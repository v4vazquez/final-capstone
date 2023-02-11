package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @RequestMapping(path = "/hosts", method = RequestMethod.GET)
    public List<User> getHosts() {
        return userDao.findHosts();
    }

    @RequestMapping(path = "/djs", method = RequestMethod.GET)
    public List<User> getDjs() {
        return userDao.findDjs();
    }

    @RequestMapping(path = "/djs/{id}", method = RequestMethod.GET)
    public User getDjById(@PathVariable int id) {
        return userDao.findDjById(id);
    }

}
