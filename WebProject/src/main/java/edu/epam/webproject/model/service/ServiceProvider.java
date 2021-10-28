package edu.epam.webproject.model.service;

import edu.epam.webproject.model.service.impl.FeedbackServiceImpl;
import edu.epam.webproject.model.service.impl.UserServiceImpl;
import edu.epam.webproject.model.service.impl.VacancyServiceImpl;

/**
 * The class that provides services
 */
public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final VacancyService vacancyService = VacancyServiceImpl.getInstance();
    private static final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();

    private ServiceProvider(){}

    /**
     * Gets instance
     *
     * @return the instance of service provider
     */
    public static ServiceProvider getInstance() {
        return instance;
    }

    /**
     * Gets user service
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets vacancy service
     *
     * @return the vacancy service
     */
    public VacancyService getVacancyService() {
        return vacancyService;
    }

    /**
     * Gets feedback service
     *
     * @return the feedback service
     */
    public FeedbackService getFeedbackService() {
        return feedbackService;
    }
}
