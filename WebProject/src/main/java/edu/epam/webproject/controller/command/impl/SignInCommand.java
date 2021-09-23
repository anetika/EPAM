package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class SignInCommand implements Command {
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
                router = new Router(PagePath.USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
            }else{
                router = new Router(PagePath.SIGN_IN_PAGE, Router.RouterType.REDIRECT);
            }
        } catch (ServiceException e) {
            session.setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
