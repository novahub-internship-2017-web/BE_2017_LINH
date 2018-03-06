package com.linhtran.assignment.booksmanagement.controller;

import java.security.Principal;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.linhtran.assignment.booksmanagement.model.User;
import com.mysql.jdbc.log.Log4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.linhtran.assignment.booksmanagement.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   PasswordEncoder passwordEncoder;


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

   @GetMapping("/login-failure")
   public String loginFailure(Model model) {
      model.addAttribute("user", new User());
      model.addAttribute("loginFailured", true);
      return "home";
   }

   @GetMapping("/user/profile")
   public String userProfile(Model model, Principal principal) {
      User user = userService.searchUserByEmail(principal.getName());
      model.addAttribute("user", user);
      return "user-profile";
   }


   @GetMapping("/login-success")
   public String dashboard(HttpSession session, Authentication authentication, Model model) {
      Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
      String userEmail = authentication.getName();
      User user = userService.searchUserByEmail(userEmail);
      if (!user.isEnabled()) {
         model.addAttribute("enabledUser", false);
         model.addAttribute("user", new User());
         session.invalidate();
         return "home";
      }

      boolean isAdmin = granted.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
      if (isAdmin) {
         return "redirect:/admin/user-manager";
      } else {
         return "redirect:/book/list";
      }
   }

   @GetMapping("/403")
   public String accessDenied() {
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

   @PostMapping("/user/modify")
   public String modifyUser(
           Model model,
           @RequestParam("currentPass") String currentPass,
           @RequestParam("newPass") String newPass,
           @RequestParam("confirmNewPass") String confirmNewPass,
           @ModelAttribute("user") @Valid User user,
           Principal principal,
           BindingResult result) {

      if (result.hasErrors()) {
         return userProfile(model, principal);
      }

      User oldUser = userService.searchUserByEmail(user.getEmail());
      oldUser.setFirstName(user.getFirstName());
      oldUser.setLastName(user.getLastName());

      if (newPass.trim().isEmpty() && confirmNewPass.trim().isEmpty()) {
         model.addAttribute("updateSucceed", true);
      } else {
         int changeUserPasswordResult = userService.changeUserPassword(oldUser, currentPass, newPass, confirmNewPass);
         if (changeUserPasswordResult == 0) {//Wrong current password
            model.addAttribute("notification", "wrongCurrentPass");
         } else if (changeUserPasswordResult == 1) {//Wrong confirm password
            model.addAttribute("notification", "wrongConfirmPass");
         } else {
            model.addAttribute("updateSucceed", true);
         }
      }
      userService.updateUser(oldUser);
      return "user-profile";
   }
}
