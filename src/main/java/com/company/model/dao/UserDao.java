package com.company.model.dao;

import com.company.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User>{

    Optional<User> findByUsername(String username);

}
