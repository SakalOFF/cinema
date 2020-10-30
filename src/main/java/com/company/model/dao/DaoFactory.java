package com.company.model.dao;

import com.company.model.dao.impl.JDBCDaoFactory;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract SessionDao createSessionDao();
    public abstract FilmDao createFilmDao();
    public abstract SeatsDao createSeatsDao();
    public abstract void beginTransaction() throws SQLException;
    public abstract void commitTransaction() throws SQLException;

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

}
