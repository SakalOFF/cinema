package com.company.model.dao.mapper;

import com.company.model.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper implements Mapper<Seat> {

    @Override
    public Seat extractFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        int number = rs.getInt("number");
        long userId = rs.getLong("user_id");
        long sessionId = rs.getLong("session_id");
        return new Seat(id, number, userId, sessionId);
    }
}
