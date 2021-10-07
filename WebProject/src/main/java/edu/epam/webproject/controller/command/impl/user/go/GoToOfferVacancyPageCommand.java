package edu.epam.webproject.controller.command.impl.user.go;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToOfferVacancyPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.OFFER_VACANCY_PAGE, Router.RouterType.REDIRECT);
        return router;
    }
}
