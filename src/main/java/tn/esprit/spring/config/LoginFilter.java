package tn.esprit.spring.config;

import tn.esprit.spring.controller.ControllerEmployeImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ControllerEmployeImpl employeController =
                (ControllerEmployeImpl) httpServletRequest.getSession().getAttribute("employeController");

        if (employeController != null && employeController.getAuthenticatedUser() != null && employeController.getLoggedIn()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsf");
        }
    }

}

