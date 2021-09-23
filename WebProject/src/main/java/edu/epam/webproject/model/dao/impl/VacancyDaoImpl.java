package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.CustomConnectionPool;
import edu.epam.webproject.model.dao.VacancyDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacancyDaoImpl implements VacancyDao {
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();
    private static final VacancyDaoImpl instance = new VacancyDaoImpl();
    private static final String FIND_ALL_VACANCIES_SQL = "SELECT vacancies.vacancy_id, vacancies.logo, vacancies.position, vacancies.company, vacancies.salary, vacancies.description, vacancy_status.status, vacancies.feedback_counter " +
            "FROM vacancies JOIN vacancy_status ON vacancies.status_id = vacancy_status.status_id WHERE vacancies.status_id = ?";
    private static final String INSERT_NEW_VACANCY_SQL = "INSERT INTO vacancies (vacancy_id, logo, position, company, salary, description, status_id, recruiter_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static VacancyDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_VACANCIES_SQL)) {
             statement.setInt(1, status.getValue());
             ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
                 vacancies.add(createVacancy(resultSet));
             }
        } catch (SQLException e) {
             throw new DaoException("Unable to handle VacancyDao.getVacanciesByStatus", e);
        }
        return vacancies;
    }

    @Override
    public long addNewVacancy(long vacancy_id, String logo, String position, String company, String salary, String description, long status_id, long recruiter_id, int feedback_counter) throws DaoException {
        return 0;
    }

    private static Vacancy createVacancy(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(resultSet.getLong(ColumnName.VACANCY_ID));
        vacancy.setLogo(resultSet.getString(ColumnName.LOGO));
        vacancy.setPosition(resultSet.getString(ColumnName.POSITION));
        vacancy.setCompany(resultSet.getString(ColumnName.COMPANY));
        vacancy.setSalary(resultSet.getBigDecimal(ColumnName.SALARY));
        vacancy.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        vacancy.setStatus(Vacancy.VacancyStatus.valueOf(resultSet.getString(ColumnName.VACANCY_STATUS)));
        return vacancy;
    }
}
