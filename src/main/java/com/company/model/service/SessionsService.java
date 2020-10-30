package com.company.model.service;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.FilmDao;
import com.company.model.dao.SeatsDao;
import com.company.model.dao.SessionDao;
import com.company.model.dto.SessionDto;
import com.company.model.entity.Film;
import com.company.model.entity.Session;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SessionsService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Session> getAllSessions(int dayId, long filmId, String sorting, String filter){
        List<SessionDto> sessionDtos = findAllByDayIdAndFilmIdWithSortingAndFiltered(dayId, filmId, sorting, filter);
        List<Session> sessions = new LinkedList<>();
        try (FilmDao filmDao = daoFactory.createFilmDao();
             SeatsDao seatsDao = daoFactory.createSeatsDao()){

            for (SessionDto dto : sessionDtos) {

                dto.session.setFilm(filmDao.findById(dto.film_id));
                dto.session.setOccupiedSeats(seatsDao.findAllBySessionId(dto.session.getId()));

                sessions.add(dto.session);
            }

        }
        return sessions;
    }

    public List<SessionDto> findAllByDayIdAndFilmIdWithSortingAndFiltered(int dayId, long filmId, String sorting, String filter) {
        if (filmId == 0){
            return findAllByDayIdWithSortingAndFiltered(dayId, sorting, filter);
        }
        try (SessionDao dao = daoFactory.createSessionDao()){
            if ("true".equals(filter)){
                return dao.findAllByDayIdAndFilmIdAndSeatsCounterLessThan(dayId, filmId, 100, sorting);
            }
            return dao.findAllByDayIdAndFilmId(dayId, filmId, sorting);
        }

    }

    public List<SessionDto> findAllByDayIdWithSorting(int id, String sorting) {
        try (SessionDao dao = daoFactory.createSessionDao()){
            return dao.findAllByDayId(id, sorting);
        }
    }

    public List<SessionDto> findAllByDayIdWithSortingAndFiltered(int id, String sorting, String filtered) {
        if ("true".equals(filtered)){
            try (SessionDao dao = daoFactory.createSessionDao()){
                return dao.findAllByDayIdAndSeatsCounterLessThan(id, 100, sorting);
            }
        }
        return findAllByDayIdWithSorting(id, sorting);
    }

    public boolean removeSession(long sessionId) {
        try (SessionDao dao = daoFactory.createSessionDao()){
            return dao.delete(sessionId);
        }
    }

    public void putSession(long filmId, int dayId, Date startTime) {
        try (SessionDao dao = daoFactory.createSessionDao()){
            dao.create(new Session(new Film(filmId), dayId, startTime));
        }
    }
}
