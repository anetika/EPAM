package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.VacancyDao;
import edu.epam.webproject.model.dao.impl.VacancyDaoImpl;
import edu.epam.webproject.model.service.VacancyService;

import java.util.List;

public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
    private static final VacancyServiceImpl instance = new VacancyServiceImpl();

    private VacancyServiceImpl(){}

    public static VacancyServiceImpl getInstance(){
        return instance;
    }
    @Override
    public List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws ServiceException {
        try {
            return vacancyDao.findVacanciesByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute a findVacanciesByStatus request", e);
        }
    }
}
