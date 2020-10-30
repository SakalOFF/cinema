package com.company.model.dao.impl;

import com.company.model.dao.SessionDao;
import com.company.model.dao.mapper.SessionMapper;
import com.company.model.dto.SessionDto;
import com.company.model.entity.Session;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCSessionDao implements SessionDao {

    private final Connection connection;
    final static Logger logger = Logger.getLogger(JDBCDaoFactory.class);

    JDBCSessionDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Session entity) {
        try (PreparedStatement findStatement = connection.prepareCall(
                "SELECT * from sessions where day_id = ? and start_time = ?"
        );
             PreparedStatement ps = connection.prepareCall(
                     "INSERT INTO sessions (start_time, day_id, film_id) VALUES (?, ?, ?)"
        )){
            findStatement.setInt(1, entity.getDayId());
            findStatement.setObject(2, entity.getStartTime());

            ResultSet rs = findStatement.executeQuery();
            if(rs.next()){
                return false;
            }

            ps.setObject(1, entity.getStartTime());
            ps.setInt(2, entity.getDayId());
            ps.setLong(3, entity.getFilm().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public Session findById(long id) {
        return null;
    }

    @Override
    public List<Session> findAll() {
        return null;
    }

    @Override
    public void update(Session entity) {

    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement ps = connection.prepareCall(
                "DELETE FROM sessions WHERE id=?"
        )){
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SessionDto> findAllByDayIdAndFilmIdAndSeatsCounterLessThan(int dayId, long filmId, int seatsCounter, String sorting) {
        try(PreparedStatement ps = connection.prepareCall(
                "select * from sessions session join films film on film.id = session.film_id  where day_id=? and film_id=? and seats_counter < ? order by " + sorting
        )){
            ps.setInt(1, dayId);
            ps.setLong(2, filmId);
            ps.setInt(3, seatsCounter);
            return executePreparedStatement(ps);
        }catch (SQLException ex){
            logger.error(ex);
            return new LinkedList<>();
        }
    }

    @Override
    public List<SessionDto> findAllByDayIdAndFilmId(int dayId, long filmId, String sorting) {
        try(PreparedStatement ps = connection.prepareCall(
                "select * from sessions session join films film on film.id = session.film_id  where day_id=? and film_id=? order by " + sorting
        )){
            ps.setInt(1, dayId);
            ps.setLong(2, filmId);
            return executePreparedStatement(ps);
        }catch (SQLException ex){
            logger.error(ex);
            return new LinkedList<>();
        }
    }

    @Override
    public List<SessionDto> findAllByDayId(int id, String sorting) {
        try(PreparedStatement ps = connection.prepareCall(
                "select * from sessions session join films film on film.id = session.film_id  where day_id=? order by " + sorting
        )){
            ps.setInt(1, id);
            return executePreparedStatement(ps);
        }catch (SQLException ex){
            logger.error(ex);
            return new LinkedList<>();
        }
    }

    @Override
    public List<SessionDto> findAllByDayIdAndSeatsCounterLessThan(int id, int seatsCounter, String sorting) {
        try(PreparedStatement ps = connection.prepareCall(
                "select * from sessions session join films film on film.id = session.film_id where day_id=? and seats_counter < ? order by " + sorting
        )){
            ps.setInt(1, id);
            ps.setInt(2, seatsCounter);
            return executePreparedStatement(ps);
        }catch (SQLException ex){
            logger.error(ex);
            return new LinkedList<>();
        }
    }

    private List<SessionDto> executePreparedStatement(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        List<SessionDto> result = new LinkedList<>();
        SessionMapper mapper = new SessionMapper();

        while (rs.next()){
            result.add(mapper.extractFromResultSet(rs));
        }
        return result;
    }
}
