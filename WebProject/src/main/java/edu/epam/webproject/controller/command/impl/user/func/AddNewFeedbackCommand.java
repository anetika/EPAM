package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.FeedbackService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewFeedbackCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        long vacancy_id = Long.parseLong(request.getParameter(RequestParameter.VACANCY_ID));
        String letter = request.getParameter(RequestParameter.LETTER);

        Date date = new Date();

        ServiceProvider provider = ServiceProvider.getInstance();
        FeedbackService service = provider.getFeedbackService();

        try {
            service.addNewFeedback(vacancy_id, letter, date);
            router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
