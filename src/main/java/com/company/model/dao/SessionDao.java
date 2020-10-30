package com.company.model.dao;

import com.company.model.dto.SessionDto;
import com.company.model.entity.Session;

import java.sql.SQLException;
import java.util.List;

public interface SessionDao extends GenericDao<Session>{
    List<SessionDto> findAllByDayIdAndFilmIdAndSeatsCounterLessThan(int dayId, long filmId, int i, String sorting);

    List<SessionDto> findAllByDayIdAndFilmId(int dayId, long filmId, String sorting);

    List<SessionDto> findAllByDayId(int id, String sorting);

    List<SessionDto> findAllByDayIdAndSeatsCounterLessThan(int id, int i, String sorting);
}
