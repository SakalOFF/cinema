package com.company.model.dao.impl;

import com.company.model.dao.UserDao;
import com.company.model.dao.mapper.UserMapper;
import com.company.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {

    private final Connection connection;
    final static Logger logger = Logger.getLogger(JDBCDaoFactory.class);

    JDBCUserDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(User user) {
        try(PreparedStatement ps = connection.prepareCall(
                "INSERT INTO users (username, password, active, role) VALUES (?, ?, ?, ?)"
        )){
            ps.setString( 1, user.getUsername());
            ps.setString( 2, user.getPassword());
            ps.setBoolean( 3, user.isActive());
            ps.setString(4, user.getRole().name());
            ps.executeUpdate();
            return true;
        }catch (Exception ex){
            logger.error(ex);
            return false;
        }
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

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

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE username = ?")){
            ps.setString( 1, username);

            ResultSet rs = ps.executeQuery();

            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            logger.error(ex);
        }
        return result;
    }
}
