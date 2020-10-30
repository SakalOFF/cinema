package com.company.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Command{
    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {
        req.getSession().invalidate();
        return "redirect:/cinema";
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse response) {
        return executeGet(req, response);
    }

    @Override
    public String executeDelete(HttpServletRequest req, HttpServletResponse response) {
        return executeGet(req, response);
    }
}
