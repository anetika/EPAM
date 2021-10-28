package edu.epam.webproject.controller.command.impl.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToForgerPasswordPage implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.FORGET_PASSWORD_PAGE, Router.RouterType.REDIRECT);
        return router;
    }
}
