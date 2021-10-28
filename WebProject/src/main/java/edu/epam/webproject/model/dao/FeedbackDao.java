package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;

import java.util.Date;
import java.util.List;

/**
 * The interface provides methods to interact with {@link Feedback} data from database
 */
public interface FeedbackDao {
    /**
     * Adds new feedback
     *
     * @param user_id - user id
     * @param vacancy_id - vacancy id
     * @param letter - letter
     * @param date - date of adding
     * @throws DaoException when problems with database connection occur
     */
    void addNewFeedback(long user_id, long vacancy_id, String letter, Date date) throws DaoException;

    /**
     * Finds list of feedbacks by status
     *
     * @param status - status of feedback
     * @return the list of feedbacks
     * @throws DaoException when problems with database connection occur
     */
    List<Feedback> findFeedbacksByStatus(Feedback.FeedbackStatus status) throws DaoException;

    /**
     * Finds list of feedbacks by vacancy id
     *
     * @param id - vacancy id
     * @return the list of feedbacks
     * @throws DaoException when problems with database connection occur
     */
    List<Feedback> findFeedbacksByVacancyId(long id) throws DaoException;

    /**
     * Finds list of feedbacks by user id
     *
     * @param id - user id
     * @return the list of feedbacks
     * @throws DaoException when problems with database connection occur
     */
    List<Feedback> findFeedbacksByUserId(long id) throws DaoException;
}
