package edu.epam.webproject.controller.command.impl.go;

import edu.epam.webproject.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;

public class GoToChangeUserPasswordPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        request.setAttribute(RequestAttribute.EMAIL, email);
        Router router = new Router(PagePath.CHANGE_PASSWORD_PAGE, Router.RouterType.FORWARD);
        return router;
    }
}
