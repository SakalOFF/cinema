package com.company.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    String executeGet(HttpServletRequest req, HttpServletResponse response);

    String executePost(HttpServletRequest req, HttpServletResponse response);

    String executeDelete(HttpServletRequest req, HttpServletResponse response);

}
