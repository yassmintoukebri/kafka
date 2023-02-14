package com.microservices.springkafkaproducer.controller;

import com.microservices.springkafkaproducer.bean.User;
import com.microservices.springkafkaproducer.serviceinterface.InterfaceGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private InterfaceGeneric<User> userServiceImplementation;


    @PostMapping
    public User saveUser(@RequestBody User user) {
      return userServiceImplementation.create(user);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userServiceImplementation.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userServiceImplementation.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id ,@RequestBody User user) {
        return userServiceImplementation.updateYourEntity(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userServiceImplementation.delete(id);
    }




}

