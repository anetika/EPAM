package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatedPassword = request.getParameter(RequestParameter.REPEATED_PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        try {
            if (userService.changeUserPasswordByEmail(email, password, repeatedPassword)) {
                router = new Router(PagePath.SIGN_IN_PAGE, Router.RouterType.REDIRECT);
            } else {
                request.setAttribute(RequestAttribute.EMAIL, email);
                router = new Router(PagePath.CHANGE_PASSWORD_PAGE, Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("Internal server error in ChangeUserPasswordCommand", e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
