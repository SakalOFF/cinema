package com.company.model.dao.impl;

import com.company.model.dao.SeatsDao;
import com.company.model.dao.mapper.SeatMapper;
import com.company.model.entity.Seat;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCSeatsDao implements SeatsDao {

    private final Connection connection;
    final static Logger logger = Logger.getLogger(JDBCDaoFactory.class);

    JDBCSeatsDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Seat> findAllBySessionId(long id) {
        try (PreparedStatement ps = connection.prepareCall(
                "SELECT * from seats where session_id=?"
        )){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            List<Seat> seats = new LinkedList<>();
            SeatMapper mapper = new SeatMapper();

            while (rs.next()){
                seats.add(mapper.extractFromResultSet(rs));
            }

            return seats;

        }catch (SQLException ex){
            logger.warn(ex);
            return new LinkedList<>();
        }
    }

    @Override
    public boolean insertAllSeatNumbers(long userId, long sessionId, List<Integer> seats) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement ps;
            for (Integer number : seats) {
                ps = connection.prepareCall("INSERT INTO seats (number, session_id, user_id) VALUES (?, ?, ?)");
                ps.setInt(1, number);
                ps.setLong(2, sessionId);
                ps.setLong(3, userId);
                ps.executeUpdate();
                ps.close();
            }
            ps = connection.prepareCall("UPDATE sessions SET seats_counter = seats_counter + ? WHERE id = ?");
            ps.setInt(1, seats.size());
            ps.setLong(2, sessionId);
            ps.executeUpdate();
            ps.close();

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex);
                throw new RuntimeException();
            }
            logger.warn(e);
            return false;
        }
    }

    @Override
    public boolean create(Seat entity) {
        try (PreparedStatement ps = connection.prepareCall(
                "INSERT INTO seats (number, session_id, user_id) VALUES (?, ?, ?)"
        )){
            ps.setInt(1, entity.getNumber());
            ps.setLong(2, entity.getSessionId());
            ps.setLong(3, entity.getUserId());
            ps.executeUpdate();
            return true;
        }catch (SQLException ex){
            logger.error(ex);
            return false;
        }
    }

    @Override
    public Seat findById(long id) {
        return null;
    }

    @Override
    public List<Seat> findAll() {
        return null;
    }

    @Override
    public void update(Seat entity) {

    }

    @Override
    public boolean delete(long id) {
        return false;
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
}
