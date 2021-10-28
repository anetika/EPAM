package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface provides methods to implement business logic connected with {@link Vacancy}
 */
public interface VacancyService {
    /**
     * Finds list of vacancies by status
     *
     * @param status - vacancy status
     * @return the list of vacancies
     * @throws ServiceException when problems with execution of requests occur
     */
    List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws ServiceException;

    /**
     * Adds new vacancy
     *
     * @param logo - logo
     * @param position - position
     * @param company - company
     * @param salary - salary
     * @param description - description of vacancy
     * @param recruiter_id - recruiter id
     * @throws ServiceException when problems with execution of requests occur
     */
    void addNewVacancy(String logo, String position, String company, BigDecimal salary, String description, long recruiter_id) throws ServiceException;

    /**
     * Changes vacancy status
     *
     * @param vacancyStatus - vacancy status
     * @param id - id of vacancy
     * @throws ServiceException when problems with execution of requests occur
     */
    void changeVacancyStatus(Vacancy.VacancyStatus vacancyStatus, long id) throws ServiceException;

    /**
     * Finds list of vacancies by recruiter id
     *
     * @param id - id of recruiter
     * @return the list of vacancies
     * @throws ServiceException when problems with execution of requests occur
     */
    List<Vacancy> findVacanciesByRecruiterId(long id) throws ServiceException;

    /**
     * Finds list of all vacancies except vacancies that were offered by user that is using platform at this moment
     *
     * @param id - id of user
     * @return the list of vacancies
     * @throws ServiceException when problems with execution of requests occur
     */
    List<Vacancy> findAllVacanciesExceptUsers(long id) throws ServiceException;
}
