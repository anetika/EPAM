package edu.epam.webproject.controller.command.impl.admin.go;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToAllUsersPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            List<User> users = userService.findAllUsers();
            request.setAttribute(RequestAttribute.USERS_LIST, users);
            router = new Router(PagePath.ALL_USERS_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Internal server error in GoToAllUsersPageCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
