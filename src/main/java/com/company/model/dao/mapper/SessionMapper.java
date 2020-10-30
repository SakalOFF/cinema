package com.company.model.dao.mapper;

import com.company.model.dto.SessionDto;
import com.company.model.entity.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SessionMapper implements Mapper<SessionDto> {
    @Override
    public SessionDto extractFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        int seatsCounter = rs.getInt("seats_counter");
        Date startTime = rs.getTime("start_time");
        int dayId = rs.getInt("day_id");
        long filmId = rs.getLong("film_id");

        Session session = new Session(id, seatsCounter, startTime, dayId);
        return new SessionDto(session, filmId);
    }
}
