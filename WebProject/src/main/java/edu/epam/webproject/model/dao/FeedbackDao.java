package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface FeedbackDao {
    void addNewFeedback(long vacancy_id, String letter, Date date) throws DaoException;
    List<Feedback> findFeedbacksByStatus(Feedback.FeedbackStatus status) throws DaoException;
    List<Feedback> findFeedbacksByVacancyId(long id) throws DaoException;
}
