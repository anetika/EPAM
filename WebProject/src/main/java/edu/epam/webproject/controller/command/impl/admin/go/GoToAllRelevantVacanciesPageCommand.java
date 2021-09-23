package edu.epam.webproject.controller.command.impl.admin.go;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.VacancyService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GoToAllRelevantVacanciesPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        ServiceProvider provider = ServiceProvider.getInstance();
        VacancyService service = provider.getVacancyService();

        try {
            List<Vacancy> vacancies = service.findVacanciesByStatus(Vacancy.VacancyStatus.RELEVANT);
            request.setAttribute(RequestAttribute.VACANCIES_LIST, vacancies);
            router = new Router(PagePath.ALL_RELEVANT_VACANCIES_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
