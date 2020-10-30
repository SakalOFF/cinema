package com.company.model.service;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.SeatsDao;
import com.company.model.entity.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class SeatsService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Integer> getOccupiedSeatsNumbers(long filmSessionId) {
        try(SeatsDao dao = daoFactory.createSeatsDao()){
            return dao.findAllBySessionId(filmSessionId).stream().map(Seat::getNumber).collect(Collectors.toList());
        }
    }

    public boolean insertAll(long userId, long filmSessionId, List<Integer> seats) {
        try (SeatsDao dao = daoFactory.createSeatsDao()){
            return dao.insertAllSeatNumbers(userId, filmSessionId, seats);
        }
    }
}
