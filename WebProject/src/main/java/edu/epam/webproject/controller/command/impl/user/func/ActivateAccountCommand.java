package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActivateAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String email = request.getParameter(RequestParameter.EMAIL);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            userService.activateUserByEmail(email);
            User user = userService.getUserByEmail(email);

            request.getSession().setAttribute(SessionAttribute.ROLE, User.Role.USER);
            request.getSession().setAttribute(SessionAttribute.USER, user);
            router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE_COMMAND, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Internal server error in ActivateAccountCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
