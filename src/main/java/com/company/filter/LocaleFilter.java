package com.company.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String requestLocale = req.getParameter("lang");

        if(requestLocale != null){
            session.setAttribute("lang", requestLocale);
        } else if (session.getAttribute("lang") == null){
            session.setAttribute("lang", "ukr");
        }

        chain.doFilter(request, response);
    }

}
