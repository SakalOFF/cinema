package com.company.controller.command;

import com.company.controller.Days;
import com.company.controller.Sorting;
import com.company.model.entity.Session;
import com.company.model.service.SessionsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.company.controller.command.MainPage.listToGrid;

public class Redactor implements Command{

    private final SessionsService sessionsService = new SessionsService();

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {
        String day = req.getParameter("day");

        int todayId = Days.getTodayWeekDayId();
        req.setAttribute("todayId", todayId);
        req.setAttribute("loggedIn", true);
        req.setAttribute("admin", true);

        int dayId = (day == null) ? 0 : Integer.parseInt(day);

        List<Session> sessions = sessionsService.getAllSessions(dayId, 0, Sorting.getSorting(""), "false");
        sessions.add(null);
        req.setAttribute("sessions", listToGrid(sessions, 4));
        req.setAttribute("days", Days.values());
        return "/pages/jsp/redactor.jsp";
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
    }

    @Override
    public String executeDelete(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
    }
}
