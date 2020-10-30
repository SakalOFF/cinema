package com.company.model.service;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.UserDao;
import com.company.model.entity.User;

import java.util.Optional;

public class UserService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();


    public boolean saveUser(User user){
        try (UserDao dao = daoFactory.createUserDao()){
            return dao.create(user);
        }
    }

    public User logIn(User user){
        try (UserDao dao = daoFactory.createUserDao()){
            Optional<User> receivedUser = dao.findByUsername(user.getUsername());

            if (receivedUser.isPresent() && user.equals(receivedUser.get())){
                return receivedUser.get();
            }

            return null;
        }
    }

}
