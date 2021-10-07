package edu.epam.webproject.controller.command.impl.admin.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.VacancyService;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeVacancyStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;


        long id = Long.parseLong(request.getParameter(RequestParameter.VACANCY_ID));
        Vacancy.VacancyStatus status = Vacancy.VacancyStatus.valueOf(request.getParameter(RequestParameter.STATUS));

        ServiceProvider provider = ServiceProvider.getInstance();
        VacancyService service = provider.getVacancyService();

        User.Role role = (User.Role) request.getSession().getAttribute(RequestAttribute.ROLE);

        try {
            service.changeVacancyStatus(status, id);
            if (role == User.Role.ADMIN) {
                if (status == Vacancy.VacancyStatus.IRRELEVANT){
                    router = new Router(PagePath.GO_TO_ALL_RELEVANT_VACANCIES_PAGE_COMMAND, Router.RouterType.REDIRECT);
                }else{
                    router = new Router(PagePath.GO_TO_ALL_IRRELEVANT_VACANCIES_PAGE_COMMAND, Router.RouterType.REDIRECT);
                }
            } else {
                router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE_COMMAND, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
