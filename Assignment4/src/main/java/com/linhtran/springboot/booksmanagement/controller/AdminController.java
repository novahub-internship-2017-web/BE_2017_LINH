package com.linhtran.springboot.booksmanagement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.request.BlockForm;
import com.linhtran.springboot.booksmanagement.service.UserService;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/admin/user-manager")
    public String userManager(Model model) {
        model.addAttribute("users", userService.listAll());
        return "user-manager";
    }

    @JsonView(Views.Public.class)
    @PutMapping("/admin/enabled")
    public User enableAndDisableUser(@RequestBody BlockForm blockUserForm) {
        User user = userService.searchUserById(blockUserForm.getId());
        user.setEnabled(blockUserForm.isEnabled());
        userService.update(user);
        return user;
    }
}
