package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.VacancyService;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public class AddNewVacancyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String logo = request.getParameter(RequestParameter.LOGO);
        String position = request.getParameter(RequestParameter.POSITION);
        String company = request.getParameter(RequestParameter.COMPANY);
        BigDecimal salary = BigDecimal.valueOf(Long.parseLong(request.getParameter(RequestParameter.SALARY)));
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        long recruiter_id = ((User)(request.getSession().getAttribute(SessionAttribute.USER))).getId();

        ServiceProvider provider = ServiceProvider.getInstance();
        VacancyService service = provider.getVacancyService();

        try {
            service.addNewVacancy(logo, position, company, salary, description, recruiter_id);
            router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}

