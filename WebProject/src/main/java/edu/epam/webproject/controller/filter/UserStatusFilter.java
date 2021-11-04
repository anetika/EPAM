package edu.epam.webproject.controller.filter;

import edu.epam.webproject.controller.command.PagePath;
import edu.epam.webproject.controller.command.SessionAttribute;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.util.MailSender;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class UserStatusFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();

        User user = (User) session.getAttribute(SessionAttribute.USER);
        if(user != null && user.getUserStatus() == User.UserStatus.REJECTED){
            session.removeAttribute(SessionAttribute.USER);
            session.setAttribute(SessionAttribute.ROLE, User.Role.GUEST);
            session.setAttribute(SessionAttribute.REJECTED_MESSAGE, true);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + '/' + PagePath.ABOUT_PAGE);
        } else if(user != null && user.getUserStatus() == User.UserStatus.IN_PROGRESS){
            MailSender sender = new MailSender();
            sender.sendRegistrationMessage(user.getEmail());
            session.setAttribute(SessionAttribute.EMAIL_CONFIRM, false);
            session.removeAttribute(SessionAttribute.USER);
            session.setAttribute(SessionAttribute.ROLE, User.Role.GUEST);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + '/' + PagePath.MESSAGE_SEND_PAGE);
        } else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
