package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import edu.epam.webproject.util.MailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ForgetPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String email = request.getParameter(RequestParameter.EMAIL);
        HttpSession session = request.getSession();

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();
        try{
            boolean isUserExists = service.isUserExists(email);
            if (isUserExists){
                MailSender sender = new MailSender();
                sender.sendForgetPasswordMessage(email);
                request.setAttribute(RequestAttribute.EMAIL, email);
            }else{
                request.setAttribute(RequestAttribute.INCORRECT_DATA, true);
            }
            router = new Router(PagePath.FORGET_PASSWORD_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Internal server error in SignInCommand", e);
            session.setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
