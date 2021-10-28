package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.FeedbackService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class AddNewFeedbackCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        long vacancy_id = Long.parseLong(request.getParameter(RequestParameter.VACANCY_ID));
        String letter = request.getParameter(RequestParameter.LETTER);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        long user_id = user.getId();

        Date date = new Date();

        ServiceProvider provider = ServiceProvider.getInstance();
        FeedbackService service = provider.getFeedbackService();

        try {
            service.addNewFeedback(user_id, vacancy_id, letter, date);
            router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE_COMMAND, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Internal server error in AddNewFeedbackCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
