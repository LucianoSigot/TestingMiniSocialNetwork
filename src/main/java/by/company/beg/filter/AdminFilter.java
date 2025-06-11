package by.company.beg.filter;


import by.company.beg.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "Filter", servletNames = {"ClearChatServlet", "AdminServlet", "UserAdminServlet"}, // declara que esta clase es un filtro web
           // y que se aplica a los servlets especificados
        urlPatterns = {"/admin.jsp", "/user*"})
public class AdminFilter extends HttpFilter { //Declara la clase AdminFilter que extiende HttpFilter, lo cual permite interceptar solicitudes HTTP.

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");

        if (currentUser == null) {
            res.sendRedirect("/main.jsp");
            return;
        }

        if (currentUser.isAdmin()) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("/acc");
        }

    }

    public AdminFilter() {
        super();
    }
}
