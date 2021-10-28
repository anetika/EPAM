package edu.epam.webproject.controller.command.impl.user.go;

import edu.epam.webproject.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;

public class GoToWriteFeedbackPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        long vacancy_id = Long.parseLong(request.getParameter(RequestParameter.VACANCY_ID));
        request.setAttribute(RequestAttribute.VACANCY_ID, vacancy_id);
        Router router = new Router(PagePath.WRITE_FEEDBACK_PAGE, Router.RouterType.FORWARD);
        return router;
    }
}
