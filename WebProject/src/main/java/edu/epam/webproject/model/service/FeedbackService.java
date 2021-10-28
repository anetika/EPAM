package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * The interface provides methods to implement business logic connected with {@link Feedback}
 */
public interface FeedbackService {
    /**
     * Adds new feedback
     *
     * @param user_id - user id
     * @param vacancy_id - vacancy id
     * @param letter - letter
     * @param date - date of adding
     * @throws ServiceException when problems with execution of requests occur
     */
    void addNewFeedback(long user_id, long vacancy_id, String letter, Date date) throws ServiceException;

    /**
     * Finds list of feedbacks by status
     *
     * @param status - feedback status
     * @return the list of feedbacks
     * @throws ServiceException when problems with execution of requests occur
     */
    List<Feedback> findFeedbacksByStatus(Feedback.FeedbackStatus status) throws ServiceException;

    /**
     * Finds list of feedbacks by user id
     *
     * @param id - user id
     * @return the list of feedbacks
     * @throws ServiceException when problems with execution of requests occur
     */
    List<Feedback> findFeedbacksByUserId(long id) throws ServiceException;
}
