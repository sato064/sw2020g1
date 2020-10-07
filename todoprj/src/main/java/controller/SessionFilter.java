package controller;

import model.Student;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/TodePrj/*"})
public class SessionFilter implements Filter {

    public void init(FilterConfig Config) throws ServletException {
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session == null) {
            response.setContentType("text/html; charset=utf-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./");
            dispatcher.forward(request, response);

        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}

