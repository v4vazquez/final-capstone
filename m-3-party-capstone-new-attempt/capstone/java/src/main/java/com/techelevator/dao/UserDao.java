package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);

    List<User> findHosts();

    List<User> findDjs();

    User findDjById(int id);

    int getEventIdFromDj(User user);
}
