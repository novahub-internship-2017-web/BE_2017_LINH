package com.linhtran.springboot.booksmanagement.controller;


import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/register")
    public void registerNewUser(@RequestBody User newUser) {
        userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
