package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.FeedbackDao;
import edu.epam.webproject.model.dao.impl.FeedbackDaoImpl;
import edu.epam.webproject.model.service.FeedbackService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The class that implements Feedback service
 */
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackDao feedbackDao = FeedbackDaoImpl.getInstance();
    private static final FeedbackServiceImpl instance = new FeedbackServiceImpl();

    private FeedbackServiceImpl(){}

    /**
     * Gets instance
     *
     * @return the instance of {@link FeedbackService}
     */
    public static FeedbackServiceImpl getInstance(){
        return instance;
    }

    @Override
    public void addNewFeedback(long user_id, long vacancy_id, String letter, Date date) throws ServiceException {
        try {
            feedbackDao.addNewFeedback(user_id, vacancy_id, letter, date);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute addNewVacancy request", e);
        }
    }

    @Override
    public List<Feedback> findFeedbacksByStatus(Feedback.FeedbackStatus status) throws ServiceException {
        try {
            return feedbackDao.findFeedbacksByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute findFeedbackByStatus request", e);
        }
    }

    @Override
    public List<Feedback> findFeedbacksByUserId(long id) throws ServiceException {
        try {
            return feedbackDao.findFeedbacksByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute findFeedbackByUserId request", e);
        }
    }
}
