package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import edu.epam.webproject.util.MailSender;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        MailSender sender = new MailSender();

        try {
            Optional<User> optionalUser = userService.signUp(login, email, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                sender.sendRegistrationMessage(user.getEmail());
                request.getSession().removeAttribute(SessionAttribute.DUPLICATE_EMAIL);
                request.getSession().setAttribute(SessionAttribute.EMAIL_CONFIRM, false);
                router = new Router(PagePath.MESSAGE_SEND_PAGE, Router.RouterType.FORWARD);
            } else {
                request.getSession().setAttribute(SessionAttribute.DUPLICATE_EMAIL, true);
                router = new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Internal server error in SignUpCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
