package by.company.beg.filter;

import by.company.beg.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "SessionOnFilter", servletNames = {"RegistrationServlet", "AuthorizationServlet"}, urlPatterns = {"/main.jsp", "/reg.jsp"})
public class SessionOnFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            res.sendRedirect("/acc");
        } else {
            chain.doFilter(req, res);
        }
    }
}
