package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.CustomConnectionPool;
import edu.epam.webproject.model.dao.FeedbackDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackDaoImpl implements FeedbackDao {
    private final CustomConnectionPool pool = CustomConnectionPool.getInstance();
    private static final FeedbackDaoImpl instance = new FeedbackDaoImpl();
    private static final String INSERT_NEW_FEEDBACK_SQL = "INSERT INTO feedbacks (vacancy_id, letter, date) VALUES (?, ?, ?)";
    private static final String FIND_ALL_FEEDBACKS_SQL = "SELECT feedbacks.feedback_id, feedbacks.user_id, feedbacks.vacancy_id, feedbacks.letter, feedback_status.feedback_status_type, feedbacks.date" +
            "FROM feedbacks JOIN feedback_status ON feedbacks.status_id = feedback_status.feedback_status_id WHERE feedbacks.status_id = ?";
    private static final String FIND_EMPLOYEE_BY_ID_SQL = "SELECT users.login, users.email, users.icon FROM users WHERE users.user_id = ?";
    private static final String FIND_VACANCY_BY_ID_SQL = "SELECT vacancies.logo, vacancies.position, vacancies.company, vacancies.salary, vacancies.description" +
            " FROM vacancies WHERE vacancies.vacancy_id = ?";
    private static final String FIND_FEEDBACKS_BY_VACANCY_ID_SQL = "SELECT feedbacks.feedback_id, feedbacks.user_id, feedbacks.letter, feedbacks.status_id, feedback_status.feedback_status_type, feedbacks.date" +
            "FROM feedbacks JOIN feedback_status ON feedbacks.status_id = feedback_status.feedback_status_id WHERE feedbacks.vacancy_id = ?";

    public static FeedbackDaoImpl getInstance(){
        return instance;
    }
    @Override
    public void addNewFeedback(long vacancy_id, String letter, Date date) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_FEEDBACK_SQL)) {
            statement.setLong(AddNewFeedbackParameterIndex.VACANCY_ID, vacancy_id);
            statement.setString(AddNewFeedbackParameterIndex.LETTER, letter);
            statement.setInt(AddNewFeedbackParameterIndex.FEEDBACK_STATUS, Feedback.FeedbackStatus.IN_PROCESS.getValue());
            statement.setDate(AddNewFeedbackParameterIndex.DATE, (java.sql.Date) date);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle FeedbackDao.addNewFeedback request", e);
        }
    }

    @Override
    public List<Feedback> findFeedbacksByStatus(Feedback.FeedbackStatus status) throws DaoException {
        List<Feedback> feedbacks = new ArrayList<>();
        Connection connection = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement findAllFeedbacksStatement = connection.prepareStatement(FIND_ALL_FEEDBACKS_SQL);
            ResultSet feedbackResultSet = findAllFeedbacksStatement.executeQuery();
            while (feedbackResultSet.next()){
                long employeeId = feedbackResultSet.getLong(ColumnName.USER_ID);
                PreparedStatement findEmployeeStatement = connection.prepareStatement(FIND_EMPLOYEE_BY_ID_SQL);
                findEmployeeStatement.setLong(1, employeeId);
                ResultSet employeeResultSet = findEmployeeStatement.executeQuery();
                long vacancyId = feedbackResultSet.getLong(ColumnName.VACANCY_ID);
                PreparedStatement findVacancyStatement = connection.prepareStatement(FIND_VACANCY_BY_ID_SQL);
                findVacancyStatement.setLong(1, vacancyId);
                ResultSet vacancyResultSet = findVacancyStatement.executeQuery();
                if (employeeResultSet.next() && vacancyResultSet.next()){
                    Feedback feedback = createFeedback(feedbackResultSet, employeeResultSet, vacancyResultSet);
                    feedbacks.add(feedback);
                }
            }

        } catch (SQLException e) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in FeedbackDao.findFeedbackByStatus request", ex);
                }
            }
            throw new DaoException("Unable to handle FeedbackDao.findFeedbackByStatus request", e);
        } finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    // TODO log
                }
            }
        }
        return feedbacks;
    }

    @Override
    public List<Feedback> findFeedbacksByVacancyId(long id) throws DaoException {
        List<Feedback> feedbacks = new ArrayList<>();
        Connection connection = null;
        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(FIND_FEEDBACKS_BY_VACANCY_ID_SQL);
            statement.setLong(1, id);
            ResultSet feedbackResultSet = statement.executeQuery();
            while (feedbackResultSet.next()){

            }

        } catch (SQLException e) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in FeedbackDao.findFeedbackByStatus request", ex);
                }
            }
            throw new DaoException("Unable to handle FeedbackDao.findFeedbackByStatus request", e);
        } finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    // TODO log
                }
            }
        }
        return feedbacks;
    }

    private static Feedback createFeedback(ResultSet feedbackResultSet, ResultSet employeeResultSet, ResultSet vacancyResultSet) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackResultSet.getLong(ColumnName.FEEDBACK_ID));
        feedback.setEmployee(createEmployee(employeeResultSet));
        feedback.setVacancy(createVacancy(vacancyResultSet));
        feedback.setLetter(feedbackResultSet.getString(ColumnName.LETTER));
        feedback.setStatus(Feedback.FeedbackStatus.valueOf(feedbackResultSet.getString(ColumnName.STATUS)));
        feedback.setDate(feedbackResultSet.getDate(ColumnName.DATE));
        return feedback;
    }

    private static User createEmployee(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString(ColumnName.LOGIN));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setIcon(resultSet.getString(ColumnName.ICON));
        return user;
    }

    private static Vacancy createVacancy(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setCompany(resultSet.getString(ColumnName.COMPANY));
        vacancy.setSalary(resultSet.getBigDecimal(ColumnName.SALARY));
        vacancy.setLogo(resultSet.getString(ColumnName.LOGO));
        vacancy.setPosition(resultSet.getString(ColumnName.POSITION));
        vacancy.setDescription(resultSet.getString(ColumnName.DESCRIPTION));
        return vacancy;
    }

    private static class AddNewFeedbackParameterIndex {
        private static final int VACANCY_ID = 1;
        private static final int LETTER = 2;
        private static final int FEEDBACK_STATUS = 3;
        private static final int DATE = 4;
    }
}
