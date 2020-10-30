package com.company.controller;

import com.company.controller.command.*;
import com.company.controller.command.Error;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cinema/*")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands;

    @Override
    public void init(){
        commands = new HashMap<>();
        commands.put("", new MainPage());
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("registration", new Registration());
        commands.put("redactor", new Redactor());
        commands.put("tickets", new Tickets());
        commands.put("sessions", new SessionAdd());
        commands.put("error", new Error());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, "get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, "post");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, "delete");
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException{
        String path = req.getRequestURI().replaceAll("^/cinema", "");
        if (path.contains("/")){
            path = path.split("/")[1];
        }
        Command command = commands.getOrDefault(path, commands.get("error"));

        String page;
        try {
            switch (method) {
                case "get":
                    page = command.executeGet(req, resp);
                    break;
                case "post":
                    page = command.executePost(req, resp);
                    break;
                case "delete":
                    page = command.executeDelete(req, resp);
                    break;
                default:
                    page = "redirect:/cinema/error";
            }
        } catch (RuntimeException e){
            page = "redirect:/cinema/error";
        }

        if (page.matches("redirect:/.+")){
            resp.sendRedirect(page.replace("redirect:", ""));
        } else if (!page.equals("")){
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

}
