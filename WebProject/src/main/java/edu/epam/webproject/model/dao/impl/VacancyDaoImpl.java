package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.CustomConnectionPool;
import edu.epam.webproject.model.dao.VacancyDao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacancyDaoImpl implements VacancyDao {
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();
    private static final VacancyDaoImpl instance = new VacancyDaoImpl();
    private static final String FIND_ALL_VACANCIES_SQL = "SELECT vacancies.vacancy_id, vacancies.logo, vacancies.position, vacancies.company, vacancies.salary, vacancies.description, vacancies.status_id, vacancy_status.status, vacancies.feedback_counter " +
            "FROM vacancies JOIN vacancy_status ON vacancies.status_id = vacancy_status.status_id WHERE vacancies.status_id = ?";
    private static final String INSERT_NEW_VACANCY_SQL = "INSERT INTO vacancies (logo, vacancies.position, company, salary, description, status_id, recruiter_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_VACANCY_STATUS_SQL = "UPDATE vacancies SET status_id = ? WHERE vacancy_id = ?";
    private static final String FIND_VACANCIES_BY_RECRUITER_ID_SQL = "SELECT vacancies.vacancy_id, vacancies.logo, vacancies.position, vacancies.company, vacancies.salary, vacancies.description, vacancies.status_id, vacancies.recruiter_id" +
            "FROM vacancies WHERE vacancies.recruiter_id = ?";

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
             throw new DaoException("Unable to handle VacancyDao.getVacanciesByStatus request", e);
        }
        return vacancies;
    }

    @Override
    public void addNewVacancy(String logo, String position, String company, BigDecimal salary, String description, long recruiter_id) throws DaoException {
        try (Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_VACANCY_SQL)) {
            statement.setString(AddNewVacancyParameterIndex.LOGO, logo);
            statement.setString(AddNewVacancyParameterIndex.POSITION, position);
            statement.setString(AddNewVacancyParameterIndex.COMPANY, company);
            statement.setBigDecimal(AddNewVacancyParameterIndex.SALARY, salary);
            statement.setString(AddNewVacancyParameterIndex.DESCRIPTION, description);
            statement.setInt(AddNewVacancyParameterIndex.STATUS_ID, Vacancy.VacancyStatus.IRRELEVANT.getValue());
            statement.setLong(AddNewVacancyParameterIndex.RECRUITER_ID, recruiter_id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle VacancyDao.addNewVacancy request", e);
        }
    }

    @Override
    public void changeVacancyStatus(Vacancy.VacancyStatus vacancyStatus, long id) throws DaoException {
        try (Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_VACANCY_STATUS_SQL)) {
            statement.setInt(1, vacancyStatus.getValue());
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle VacancyDao.changeVacancyStatus", e);
        }
    }

    @Override
    public List<Vacancy> findVacanciesByRecruiterId(long id) throws DaoException {
        List<Vacancy> vacancies = new ArrayList<>();
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_VACANCIES_BY_RECRUITER_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancies.add(createVacancy(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle VacancyDao.findVacanciesByRecruiterId", e);
        }
        return vacancies;
    }

    private static Vacancy createVacancy(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(resultSet.getLong(ColumnName.VACANCY_ID));
        vacancy.setLogo(resultSet.getString(ColumnName.LOGO));
        vacancy.setPosition(resultSet.getString(ColumnName.POSITION));
        vacancy.setCompany(resultSet.getString(ColumnName.COMPANY));
        vacancy.setSalary(resultSet.getBigDecimal(ColumnName.SALARY));
        vacancy.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        vacancy.setStatus(Vacancy.VacancyStatus.valueOf(resultSet.getString(ColumnName.STATUS)));
        return vacancy;
    }

    private static class AddNewVacancyParameterIndex {
        private static final int LOGO = 1;
        private static final int POSITION = 2;
        private static final int COMPANY = 3;
        private static final int SALARY = 4;
        private static final int DESCRIPTION = 5;
        private static final int STATUS_ID = 6;
        private static final int RECRUITER_ID = 7;
    }
}
