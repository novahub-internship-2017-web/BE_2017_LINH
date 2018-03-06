package com.linhtran.assignment.booksmanagement.model;

import com.linhtran.assignment.booksmanagement.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

public class UserTest {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void getId() throws Exception {
        User user = userService.searchUserByEmail("manhlinh.sama@gmail.com");
        assertEquals(user.getPassword(), passwordEncoder.encode("tranmanhlinh"));

    }

}