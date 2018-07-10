package com.javaforjava.bootifulelasticsearch.controller;

import com.javaforjava.bootifulelasticsearch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javaforjava.bootifulelasticsearch.dal.UserDAL;

import java.util.List;

@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDAL userDAL;

    @RequestMapping("/all")
    public List<User> getAllUsers() {
        return userDAL.getAllUsers();
    }

    @RequestMapping("/id/{userId}")
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}", userId);
        User user = userDAL.getUserById(userId);
        LOG.info("User with ID: {} is {}", userId, user);
        return user;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Adding user : {}", user);
        userDAL.addNewUser(user);
        LOG.info("Added user : {}", user);
        return user;
    }

    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {
        return userDAL.getAllUserSettings(userId);
    }

    @RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
    public String getUserSetting(
            @PathVariable String userId, @PathVariable String key) {
        return userDAL.getUserSetting(userId, key);
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(
            @PathVariable String userId,
            @PathVariable String key,
            @PathVariable String value) {
        return userDAL.addUserSetting(userId, key, value);
    }
}