package com.linhtran.springboot.booksmanagement.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.service.UserService;
import com.linhtran.springboot.booksmanagement.validation.UserValidation;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @JsonView(Views.Public.class)
    @PostMapping("/users/register")
    public UserValidation registerNewUser(@RequestBody User newUser) {
        UserValidation userValidation = new UserValidation(newUser);

        if (userValidation.isValidUser()) {
            userService.register(newUser);
        }
        return userValidation;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.listAll();
    }



}
