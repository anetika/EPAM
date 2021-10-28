package edu.epam.webproject.controller.command.impl.user.go;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.entity.Vacancy;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.FeedbackService;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.VacancyService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToUserAccountPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        long id = user.getId();

        ServiceProvider provider = ServiceProvider.getInstance();
        VacancyService vacancyService = provider.getVacancyService();
        FeedbackService feedbackService = provider.getFeedbackService();

        try{
            List<Vacancy> vacancies = vacancyService.findVacanciesByRecruiterId(id);
            List<Feedback> feedbacks = feedbackService.findFeedbacksByUserId(id);
            request.setAttribute(RequestAttribute.VACANCIES_LIST, vacancies);
            request.setAttribute(RequestAttribute.FEEDBACKS_LIST, feedbacks);
            router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Internal server error in GoToUserAccountPageCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
