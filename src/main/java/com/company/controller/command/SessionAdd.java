package com.company.controller.command;

import com.company.controller.Days;
import com.company.model.service.FilmsService;
import com.company.model.service.SessionsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionAdd implements Command{

    private final SessionsService sessionsService = new SessionsService();
    private final FilmsService filmsService = new FilmsService();
    final static Logger logger = Logger.getLogger(SessionAdd.class);

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {
        req.setAttribute("loggedIn", true);
        req.setAttribute("admin", true);

        req.setAttribute("days", Days.values());
        req.setAttribute("films", filmsService.getAllFilms());
        return "/pages/jsp/session_add_form.jsp";
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse response) {

        long filmId;
        int dayId;
        Date startTime;

        try {
            filmId = Long.parseLong(req.getParameter("filmId"));
            dayId = Integer.parseInt(req.getParameter("dayId"));
            startTime = new SimpleDateFormat("HH:mm").parse(req.getParameter("startTime"));
        } catch (NumberFormatException | ParseException e) {
            logger.error(e);
            req.setAttribute("error", true);
            return "redirect:/cinema/sessions";
        }

        sessionsService.putSession(filmId, dayId, startTime);
        return "redirect:/cinema/redactor?day=" + dayId;
    }

    @Override
    public String executeDelete(HttpServletRequest req, HttpServletResponse response) {
        long sessionId;
        try {
            sessionId = Long.parseLong(req.getPathInfo().split("/")[2]);
        } catch (Exception e){
            logger.error(e);
            return "/pages/jsp/error/error.jsp";
        }

        if (sessionsService.removeSession(sessionId)){
            response.setStatus(HttpServletResponse.SC_OK);
            return "";
        }
        return "/pages/jsp/error/error.jsp";
    }
}
