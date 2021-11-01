package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.RequestParameter;
import edu.epam.webproject.controller.command.Router;
import edu.epam.webproject.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String localeStr = request.getParameter(RequestParameter.LOCALE);
        Locale locale = new Locale(localeStr);

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        String prevPage = (String) session.getAttribute(SessionAttribute.PREV_PAGE);

        Router router = new Router(prevPage, Router.RouterType.REDIRECT);
        return router;
    }
}
