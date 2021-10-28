package edu.epam.webproject.controller.command.impl.admin.go;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.Feedback;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.FeedbackService;
import edu.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToAllFeedbacksPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        Feedback.FeedbackStatus status = Feedback.FeedbackStatus.valueOf(request.getParameter(RequestParameter.STATUS));

        ServiceProvider provider = ServiceProvider.getInstance();
        FeedbackService service = provider.getFeedbackService();

        try {
            List<Feedback> feedbacks = service.findFeedbacksByStatus(status);
            request.setAttribute(RequestAttribute.FEEDBACKS_LIST, feedbacks);
            router = new Router(PagePath.ALL_FEEDBACKS_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Internal server error in GoToAllFeedbacksPageCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
