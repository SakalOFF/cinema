package com.company.controller.command;

import com.company.controller.Sorting;
import com.company.controller.Days;
import com.company.model.entity.Session;
import com.company.model.entity.User;
import com.company.model.service.SessionsService;
import com.company.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainPage implements Command{

    private final SessionsService sessionsService = new SessionsService();

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {

        String sorting = req.getParameter("sorting");
        int day = Integer.parseInt(req.getParameter("day") != null ? req.getParameter("day") : "0");
        String filter = req.getParameter("filter");
        long film = Long.parseLong(req.getParameter("film") != null ? req.getParameter("film") : "0");

        User user = AppUtils.getLoggedInUser(req.getSession());
        boolean loggedIn = user != null;
        boolean admin = false;

        if (loggedIn){
            admin = user.isAdmin();

            req.setAttribute("login", user.getUsername());
            req.setAttribute("admin", admin);
            req.setAttribute("sortingOptions", Sorting.values());
        } else {
            sorting = null;
        }

        int todayId = Days.getTodayWeekDayId();
        req.setAttribute("todayId", todayId);

        day = (day == 0) ? todayId : day;

        req.setAttribute("loggedIn", loggedIn);
        req.setAttribute("days", Days.values());

        filter = (
                (filter == null || filter.equals("true"))
                        && loggedIn
                        && !admin)
                ? "true"
                : "false";

        List<Session> sessions = sessionsService.getAllSessions(day, film, Sorting.getSorting(sorting), filter);
        req.setAttribute("sessions", listToGrid(sessions, 4));

        return "/pages/jsp/main_page.jsp";
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
    }

    @Override
    public String executeDelete(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
    }

    public static <T> List<List<T>> listToGrid(List<T> list, int columns){
        if(list == null) return null;

        List<T> row = new ArrayList<>(columns);
        List<List<T>> result = new LinkedList<>();

        for (int i = 0; i < list.size(); i++){
            row.add(list.get(i));
            if ((i+1) % columns == 0){
                result.add(row);
                row = new ArrayList<>(columns);
            }
        }

        if (0 < row.size() && row.size() < columns){
            result.add(row);
        }
        return result;
    }

}
