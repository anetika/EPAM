package edu.epam.webproject.controller.command.impl;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUserIconCommand implements Command {
    private static final String UPLOAD_ICON_DIR = "/static/img/icon";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String applicationDir = request.getServletContext().getRealPath("");
        String fullFileName = null;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(SessionAttribute.USER);
        User.Role role = (User.Role) session.getAttribute(SessionAttribute.ROLE);

        String fileName = UUID.randomUUID().toString();
        String uploadFileDir = applicationDir + File.separator + UPLOAD_ICON_DIR + File.separator;

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();
        try{
            for (Part part : request.getParts()){
                if (part.getSubmittedFileName() != null){
                    String path = part.getSubmittedFileName();
                    fullFileName = fileName + path.substring(path.lastIndexOf("."));
                    part.write(uploadFileDir + File.separator + fullFileName);
                }
            }

            String icon = UPLOAD_ICON_DIR + File.separator + fullFileName;
            user.setIcon(icon);
            session.setAttribute(SessionAttribute.USER, user);
            service.updateUserIcon(user.getId(), icon);
            if (role == User.Role.ADMIN){
                router = new Router(PagePath.ADMIN_ACCOUNT_PAGE, Router.RouterType.REDIRECT);
            } else {
                router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE_COMMAND, Router.RouterType.FORWARD);
            }
        } catch (ServletException | IOException | ServiceException e) {
            session.setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }

        return router;
    }
}
