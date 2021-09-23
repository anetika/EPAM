package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.ServiceException;

import java.util.List;

public interface VacancyService {
    List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws ServiceException;
}
