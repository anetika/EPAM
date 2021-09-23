package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.DaoException;

import java.util.List;

public interface VacancyDao {
    List<Vacancy> findVacanciesByStatus(Vacancy.VacancyStatus status) throws DaoException;
    long addNewVacancy(long vacancy_id, String logo, String position, String company, String salary, String description, long status_id, long recruiter_id, int feedback_counter) throws DaoException;
}
