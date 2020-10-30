package com.company.model.dao.mapper;

import com.company.model.entity.Role;
import com.company.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User>{
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("active"),
                Role.valueOf(rs.getString("role")));
    }
}
