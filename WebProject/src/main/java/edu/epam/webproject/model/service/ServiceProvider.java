package edu.epam.webproject.model.service;

import edu.epam.webproject.model.service.impl.UserServiceImpl;
import edu.epam.webproject.model.service.impl.VacancyServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final VacancyService vacancyService = VacancyServiceImpl.getInstance();

    private ServiceProvider(){}

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
    public VacancyService getVacancyService() {
        return vacancyService;
    }
}
