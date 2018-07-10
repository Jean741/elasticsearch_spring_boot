package com.javaforjava.bootifulelasticsearch.controller;

import com.javaforjava.bootifulelasticsearch.dal.UserRepository;
import com.javaforjava.bootifulelasticsearch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserDataController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/all")
    public List<User> getAllUsers() {
        LOG.info("Getting all users. Total: {}", userRepository.count());
        List<User> target = new ArrayList<>();
        //findAll returns an iterable, just converting it to a list for output
        userRepository.findAll().forEach(target::add);
        return target;
    }

    @RequestMapping("/id/{userId}")
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}", userId);
        User user = userRepository.findOne(userId);
        LOG.info("User with ID: {} is {}", userId, user);
        return user;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Adding user : {}", user);
        userRepository.save(user);
        LOG.info("Added user : {}", user);
        return user;
    }

    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {

        User user = userRepository.findOne(userId);
        if (user != null) {
            return user.getUserSettings();
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
    public String getUserSetting(
            @PathVariable String userId, @PathVariable String key) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            return user.getUserSettings().get(key);
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(
            @PathVariable String userId,
            @PathVariable String key,
            @PathVariable String value) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            user.getUserSettings().put(key, value);
            userRepository.save(user);
            return "Key added";
        } else {
            return "User not found.";
        }
    }
}
