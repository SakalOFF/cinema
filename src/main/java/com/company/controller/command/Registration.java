package com.company.controller.command;

import com.company.model.entity.User;
import com.company.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration implements Command{

    private final UserService userService = new UserService();

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/registration_page.jsp";
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse response) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.equals("") || password == null || password.equals("")) {
            req.setAttribute("invalidData", true);
            return "/pages/jsp/registration_page.jsp";
        }

        if (!userService.saveUser(new User(username, password))){
            req.setAttribute("userExists", true);
            return "/pages/jsp/registration_page.jsp";
        }

        return "redirect:/cinema";

    }

    @Override
    public String executeDelete(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
    }
}
