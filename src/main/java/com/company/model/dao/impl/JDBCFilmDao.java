package com.company.model.dao.impl;

import com.company.model.dao.FilmDao;
import com.company.model.dao.mapper.FilmMapper;
import com.company.model.entity.Film;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JDBCFilmDao implements FilmDao {

    private final Connection connection;
    final static Logger logger = Logger.getLogger(JDBCDaoFactory.class);

    public JDBCFilmDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Film entity) {
        return false;
    }

    @Override
    public Film findById(long id) {
        try (PreparedStatement ps = connection.prepareCall(
                "select * from films where id=?"
        )){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Film film = null;

            if (rs.next()) {
                film =  new FilmMapper().extractFromResultSet(rs);
            }

            return film;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<Film> findAll() {
        try (PreparedStatement ps = connection.prepareCall(
                "select * from films"
        )){
            ResultSet rs = ps.executeQuery();
            List<Film> films = new LinkedList<>();
            FilmMapper mapper = new FilmMapper();

            while (rs.next()) {
                films.add(mapper.extractFromResultSet(rs));
            }

            return films;
        } catch (SQLException e) {
            logger.error(e);
            return new LinkedList<>();
        }
    }

    @Override
    public void update(Film entity) {

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
