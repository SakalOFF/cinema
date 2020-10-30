package com.company.model.dao.mapper;

import com.company.model.entity.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements Mapper<Film>{
    @Override
    public Film extractFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String description = rs.getString("description");
        String imagePath = rs.getString("image_path");
        String title = rs.getString("title");
        return new Film(id, title, description, imagePath);
    }
}
