package edu.epam.webproject.controller;

import edu.epam.webproject.controller.command.Command;
import edu.epam.webproject.controller.command.CommandProvider;
import edu.epam.webproject.controller.command.RequestParameter;
import edu.epam.webproject.controller.command.Router;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet
public class Controller extends HttpServlet {
    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        Command command = commandProvider.getCommand(commandName);
        Router router = command.execute(req);
        switch (router.getType()) {
            case FORWARD:
                RequestDispatcher dispatcher = req.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(req, resp);
                break;
            case REDIRECT:
                resp.sendRedirect(router.getPagePath());
                break;
            default:
                resp.sendError(404);
        }
    }
}
