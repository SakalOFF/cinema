package com.company.controller.command;

import com.company.model.entity.User;
import com.company.model.service.UserService;
import com.company.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login implements Command{

    private final UserService userService = new UserService();

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {
        req.setAttribute("logIn", true);
        return "/pages/jsp/login.jsp";
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse response){
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && !username.equals("") && password != null && !password.equals("")) {
            User user = userService.logIn(new User(username, password));

            if (user != null){

                int redirectId = req.getParameter("redirectId") == null ? -1 : Integer.parseInt(req.getParameter("redirectId"));
                String requestUri = AppUtils.getRedirectAfterLoginUrl(req.getSession(), redirectId);

                AppUtils.storeLoggedInUser(req.getSession(), user);

                if (requestUri != null) {
                    return "redirect:" + requestUri;
                }
                return "redirect:/cinema";
            }
        }

        req.setAttribute("invalidData", true);
        req.setAttribute("logIn", true);
        return "/pages/jsp/login.jsp";
    }

    @Override
    public String executeDelete(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
    }
}
