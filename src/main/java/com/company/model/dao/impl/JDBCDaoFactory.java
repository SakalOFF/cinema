package com.company.model.dao.impl;

import com.company.model.dao.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private boolean transactionStarted;
    private Connection connection;
    final static Logger logger = Logger.getLogger(JDBCDaoFactory.class);

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(transactionStarted ? connection : getConnection());
    }

    @Override
    public SessionDao createSessionDao() {
        return new JDBCSessionDao(transactionStarted ? connection : getConnection());
    }

    @Override
    public FilmDao createFilmDao() {
        return new JDBCFilmDao(transactionStarted ? connection : getConnection());
    }

    @Override
    public SeatsDao createSeatsDao() {
        return new JDBCSeatsDao(transactionStarted ? connection : getConnection());
    }

    @Override
    public void beginTransaction() throws SQLException {
        connection = getConnection();
        connection.setAutoCommit(false);
        transactionStarted = true;
    }

    @Override
    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
        connection.close();
        transactionStarted = false;
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}
