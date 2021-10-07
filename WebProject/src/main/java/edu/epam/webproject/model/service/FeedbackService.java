package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface FeedbackService {
    void addNewFeedback(long vacancy_id, String letter, Date date) throws ServiceException;
    List<Feedback> findFeedbacksByStatus(Feedback.FeedbackStatus status) throws ServiceException;
}
