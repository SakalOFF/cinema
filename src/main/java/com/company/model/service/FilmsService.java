package com.company.model.service;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.FilmDao;
import com.company.model.entity.Film;

import java.util.List;

public class FilmsService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Film> getAllFilms(){
        try (FilmDao dao = daoFactory.createFilmDao()){
            return dao.findAll();
        }
    }

}
