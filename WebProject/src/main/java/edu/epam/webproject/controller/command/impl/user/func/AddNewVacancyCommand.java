package edu.epam.webproject.controller.command.impl.user.func;

import edu.epam.webproject.controller.command.*;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.VacancyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

public class AddNewVacancyCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String UPLOAD_LOGO_DIR = "/static/img/logo/";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        String applicationDir = request.getServletContext().getRealPath("");
        String fullFileName = null;

        String fileName = UUID.randomUUID().toString();
        String uploadFileDir = applicationDir + File.separator + UPLOAD_LOGO_DIR;

        String position = request.getParameter(RequestParameter.POSITION);
        String company = request.getParameter(RequestParameter.COMPANY);
        BigDecimal salary = BigDecimal.valueOf(Long.parseLong(request.getParameter(RequestParameter.SALARY)));
        String description = request.getParameter(RequestParameter.DESCRIPTION);
        long recruiter_id = ((User)(request.getSession().getAttribute(SessionAttribute.USER))).getId();

        ServiceProvider provider = ServiceProvider.getInstance();
        VacancyService service = provider.getVacancyService();

        try {
            for (Part part : request.getParts()){
                if (part.getSubmittedFileName() != null){
                    String path = part.getSubmittedFileName();
                    fullFileName = fileName + path.substring(path.lastIndexOf("."));
                    part.write(uploadFileDir + fullFileName);
                }
            }
            String logo = UPLOAD_LOGO_DIR + File.separator + fullFileName;
            service.addNewVacancy(logo, position, company, salary, description, recruiter_id);
            router = new Router(PagePath.GO_TO_USER_ACCOUNT_PAGE_COMMAND, Router.RouterType.FORWARD);
        } catch (ServiceException | IOException | ServletException e) {
            logger.error("Internal server error in AddNewVacancyCommand", e);
            request.getSession().setAttribute(SessionAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_500_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}

