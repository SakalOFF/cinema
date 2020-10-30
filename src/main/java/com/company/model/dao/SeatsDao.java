package com.company.model.dao;

import com.company.model.entity.Seat;

import java.util.List;

public interface SeatsDao extends GenericDao<Seat>{

    List<Seat> findAllBySessionId(long id);

    boolean insertAllSeatNumbers(long userId, long sessionId, List<Integer> seats);

}
