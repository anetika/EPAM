package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface provides methods to interact with {@link Vacancy} data from database
 */
public interface VacancyDao {
    /**
     * Finds a list of {@link Vacancy} using status
     *
     * @param status - vacancy status
     * @return list of vacancies
     * @throws DaoException when problems with database connection occur
     */
    List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws DaoException;

    /**
     * Adds new {@link Vacancy}
     *
     * @param logo - company logo
     * @param position - position
     * @param company - company
     * @param salary - salary
     * @param description - description of vacancy
     * @param recruiter_id - recruiter id
     * @throws DaoException when problems with database connection occur
     */
    void addNewVacancy(String logo, String position, String company, BigDecimal salary, String description, long recruiter_id) throws DaoException;

    /**
     * Changes status of {@link Vacancy}
     *
     * @param vacancyStatus - new vacancy status
     * @param id - id of vacancy
     * @throws DaoException when problems with database connection occur
     */
    void changeVacancyStatus(Vacancy.VacancyStatus vacancyStatus, long id) throws DaoException;

    /**
     * Finds list of {@link Vacancy} using id of recruiter
     *
     * @param id - id of recruiter
     * @return - list of vacancies
     * @throws DaoException when problems with database connection occur
     */
    List<Vacancy> findVacanciesByRecruiterId(long id) throws DaoException;

    /**
     * Finds list of {@link Vacancy} except vacancies that were offered by the user which is using platform at this moment
     *
     * @param id - user id
     * @return the list of vacancies
     * @throws DaoException when problems with database connection occur
     */
    List<Vacancy> findAllVacanciesExceptUsers(long id) throws DaoException;
}
