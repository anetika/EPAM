package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyDao {
    List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws DaoException;
    void addNewVacancy(String logo, String position, String company, BigDecimal salary, String description, long recruiter_id) throws DaoException;
    void changeVacancyStatus(Vacancy.VacancyStatus vacancyStatus, long id) throws DaoException;
    List<Vacancy> findVacanciesByRecruiterId(long id) throws DaoException;
}
