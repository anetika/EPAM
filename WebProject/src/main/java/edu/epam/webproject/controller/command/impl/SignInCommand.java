package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        HttpSession session = request.getSession();
        try{
            Optional<User> optionalUser = userService.signIn(email, password);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                session.setAttribute(SessionAttribute.USER, user);
                session.setAttribute(SessionAttribute.ROLE, user.getRole());
                if (session.getAttribute(SessionAttribute.DUPLICATE_EMAIL) != null){
                    session.removeAttribute(SessionAttribute.DUPLICATE_EMAIL);
                }
                if (user.getRole() == User.Role.USER) {
                    router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE_COMMAND, Router.RouterType.REDIRECT);
                } else {
                    router = new Router(PagePath.ADMIN_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
                }
            }else {
                router = new Router(PagePath.SIGN_IN_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Internal server error in SignInCommand", e);
            session.setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
