package com.company.controller.command;

import com.company.model.entity.Role;
import com.company.model.entity.User;
import com.company.model.service.SeatsService;
import com.company.utils.AppUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Tickets implements Command{

    private final SeatsService seatsService = new SeatsService();
    final static Logger logger = Logger.getLogger(Tickets.class);

    private int rowsQuantity;
    private int seatsInARow;

    public void getProperties(){
        Properties cinemaProperties = new Properties();
        try (InputStream propertyFile = getClass().getClassLoader().getResourceAsStream("/cinema.properties")){
            cinemaProperties.load(propertyFile);
            rowsQuantity = Integer.parseInt(cinemaProperties.get("cinema.hall.rows.quantity").toString());
            seatsInARow = Integer.parseInt(cinemaProperties.get("cinema.hall.rows.seats.quantity").toString());
            if(rowsQuantity == 0 || seatsInARow == 0) throw new IOException();
        } catch (IOException e){
            logger.error(e);
            throw new RuntimeException();
        }
    }

    @Override
    public String executeGet(HttpServletRequest req, HttpServletResponse response) {

        User user = AppUtils.getLoggedInUser(req.getSession());
        long filmSessionId;
        try {
            filmSessionId = Long.parseLong(req.getPathInfo().split("/")[2]);
        } catch (NumberFormatException e){
            logger.error(e);
            return "/pages/jsp/error/404.jsp";
        }

        if (req.getParameter("error") != null){
            req.setAttribute("message", true);
        }
        req.setAttribute("loggedIn", true);
        req.setAttribute("admin", user.isAdmin());

        getProperties();
        req.setAttribute("rowsQuantity", rowsQuantity);
        req.setAttribute("seatsInARow", seatsInARow);

        req.setAttribute("filmSession", filmSessionId);

        List<Integer> occupiedSeats = seatsService.getOccupiedSeatsNumbers(filmSessionId);
        req.setAttribute("bookedSeats", occupiedSeats);
        return "/pages/jsp/tickets.jsp";
    }

    @Override
    public String executePost(HttpServletRequest req, HttpServletResponse response) {
        User user = AppUtils.getLoggedInUser(req.getSession());
        if (!user.getRole().equals(Role.USER)) return "/pages/jsp/error/error.jsp";

        long filmSessionId;
        List<Integer> seats;

        try {
            filmSessionId = Long.parseLong(req.getParameter("filmSessionId"));
            String[] basket = req.getParameterValues("basket[]");
            seats = Arrays.stream(basket).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e){
            logger.error(e);
            return "/pages/jsp/error/error.jsp";
        }

        if (!seatsService.insertAll(user.getId(), filmSessionId, seats)){
            return "redirect:/cinema/tickets/" + filmSessionId + "?error";
        }
        return "redirect:/cinema/tickets/" + filmSessionId;
    }

    @Override
    public String executeDelete(HttpServletRequest req, HttpServletResponse response) {
        return "/pages/jsp/error/error.jsp";
    }
}
