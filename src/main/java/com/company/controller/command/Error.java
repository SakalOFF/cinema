package com.company.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Error implements Command{


    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
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
