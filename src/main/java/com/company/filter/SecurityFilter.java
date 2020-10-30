package com.company.filter;

import com.company.model.entity.Role;
import com.company.model.entity.User;
import com.company.utils.AppUtils;
import com.company.utils.SecurityUtils;
import com.company.utils.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/cinema/*")
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getPathInfo() == null ? "" : request.getPathInfo();
        if(servletPath.contains("/")){
            servletPath = servletPath.split("/")[1];
        }

        User loggedInUser = AppUtils.getLoggedInUser(request.getSession());

        if (servletPath.equals("login") || servletPath.equals("registration")) {
            if (loggedInUser != null){
                response.sendRedirect("/cinema");
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;
        if (loggedInUser != null) {
            String userName = loggedInUser.getUsername();
            Role role = loggedInUser.getRole();

            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }

        if (SecurityUtils.isSecurityPage(request)) {

            if (loggedInUser == null) {
                String requestUri = request.getRequestURI();
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
                response.sendRedirect(wrapRequest.getContextPath() + "/cinema/login?redirectId=" + redirectId);
                return;
            }

            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/pages/jsp/error/404.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }
}
