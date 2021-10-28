package edu.epam.webproject.controller.command.impl.admin.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String email = request.getParameter(RequestParameter.EMAIL);
        User.UserStatus status = User.UserStatus.valueOf(request.getParameter(RequestParameter.STATUS));

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        try{
            service.changeUserStatusByEmail(email, status);
            router = new Router(PagePath.GO_TO_ALL_USERS_PAGE_COMMAND, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Internal server error in ChangeUserStatusCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
