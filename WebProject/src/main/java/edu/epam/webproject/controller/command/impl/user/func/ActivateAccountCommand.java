package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class ActivateAccountCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String email = request.getParameter(RequestParameter.EMAIL);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        try {
            userService.activateUserByEmail(email);
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            if (user != null) {
                user.setUserStatus(User.UserStatus.APPROVED);
                request.getSession().setAttribute(SessionAttribute.USER, user);
                router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
            }else {
                router = new Router(PagePath.SIGN_IN_PAGE, Router.RouterType.REDIRECT);
            }

        } catch (ServiceException e) {
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
