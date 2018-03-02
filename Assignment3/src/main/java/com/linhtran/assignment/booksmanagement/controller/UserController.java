package com.linhtran.assignment.booksmanagement.controller;

import java.security.Principal;
import javax.validation.Valid;

import com.linhtran.assignment.booksmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.linhtran.assignment.booksmanagement.service.UserService;


@Controller
public class UserController {

   @Autowired
   private UserService userService;

   @GetMapping("/")
   public String userForm(Model model, Principal principal) {

      if (principal == null) {
         model.addAttribute("user", new User());
         model.addAttribute("users", userService.list());
         return "home";
      } else {
         return "redirect:/book/list";
      }
   }


   @GetMapping("/login-success")
   public String dashboard() {
      return "redirect:/book/list";
   }

   @PostMapping("/register")
   public String saveUser(@ModelAttribute("user") @Valid User user,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         model.addAttribute("users", userService.list());
         return "home";
      }

      if (!user.getPassword().equals(user.getConfirmPassword())) {
         model.addAttribute("confirmError", true);
         return "home";
      }

      User registeredUser = userService.searchUserByEmail(user.getEmail());
      if (registeredUser != null) {
         model.addAttribute("isTakenEmail", true);
         return "home";
      }

      userService.register(user);
      model.addAttribute("user", new User());
      model.addAttribute("registerSucceed", true);
      return "home";
   }
}
