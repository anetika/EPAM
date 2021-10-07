package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyService {
    List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws ServiceException;
    void addNewVacancy(String logo, String position, String company, BigDecimal salary, String description, long recruiter_id) throws ServiceException;
    void changeVacancyStatus(Vacancy.VacancyStatus vacancyStatus, long id) throws ServiceException;
    List<Vacancy> findVacanciesByRecruiterId(long id) throws ServiceException;
}
