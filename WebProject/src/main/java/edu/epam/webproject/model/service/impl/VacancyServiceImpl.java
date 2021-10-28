package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.VacancyDao;
import edu.epam.webproject.model.dao.impl.VacancyDaoImpl;
import edu.epam.webproject.model.service.VacancyService;
import edu.epam.webproject.validator.VacancyValidator;

import java.math.BigDecimal;
import java.util.List;

/**
 * The class that implements Vacancy service
 */
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao = VacancyDaoImpl.getInstance();
    private static final VacancyServiceImpl instance = new VacancyServiceImpl();
    private final VacancyValidator validator = new VacancyValidator();

    private VacancyServiceImpl(){}

    /**
     * Gets instance
     *
     * @return the instance of {@link VacancyService}
     */
    public static VacancyServiceImpl getInstance(){
        return instance;
    }
    @Override
    public List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws ServiceException {
        try {
            return vacancyDao.findVacanciesByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute findVacanciesByStatus request", e);
        }
    }

    @Override
    public void addNewVacancy(String logo, String position, String company, BigDecimal salary, String description, long recruiter_id) throws ServiceException {
        try {
            if (validator.validateVacancy(position, company, salary, description)) {
                vacancyDao.addNewVacancy(logo, position, company, salary, description, recruiter_id);
            } else {
                throw new ServiceException("Wrong input");
            }
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute addNewVacancy request", e);
        }
    }

    @Override
    public void changeVacancyStatus(Vacancy.VacancyStatus vacancyStatus, long id) throws ServiceException {
        try {
            vacancyDao.changeVacancyStatus(vacancyStatus, id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute changeVacancyStatus request", e);
        }
    }

    @Override
    public List<Vacancy> findVacanciesByRecruiterId(long id) throws ServiceException {
        try {
            return vacancyDao.findVacanciesByRecruiterId(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute findVacanciesByRecruiterId", e);
        }
    }

    @Override
    public List<Vacancy> findAllVacanciesExceptUsers(long id) throws ServiceException {
        try{
            return vacancyDao.findAllVacanciesExceptUsers(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute findAllVacanciesExceptUsers", e);
        }
    }
}
