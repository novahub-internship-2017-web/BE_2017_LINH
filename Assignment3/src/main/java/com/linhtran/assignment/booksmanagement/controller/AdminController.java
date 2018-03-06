package com.linhtran.assignment.booksmanagement.controller;


import com.linhtran.assignment.booksmanagement.model.User;
import com.linhtran.assignment.booksmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {

   @Autowired
   private UserService userService;


   @GetMapping("/admin/user-manager")
   public String listBook(Model model) {
      model.addAttribute("users", userService.list());
      return "user-manager";
   }

   @GetMapping("/admin/delete")
   public String deleteUser(
           Model model,
           @RequestParam("id") int id ) {
      User user = userService.searchUserById(id);
      if (user.getRole().equals("ADMIN")) {
         model.addAttribute("deleteSucceed", false);
      } else {
         userService.deleteUser(id);
         model.addAttribute("deleteSucceed", true);
         model.addAttribute("userEmail", user.getEmail());
      }
      return listBook(model);
   }

   @PostMapping("/admin/enabled")
   public void enableAndDisableUser(
           @RequestParam("id") int id,
           @RequestParam("value") int value ) {
      User user = userService.searchUserById(id);
      if (value == 1) {
         user.setEnabled(true);
      } else {
         user.setEnabled(false);
      }
      userService.updateUser(user);
   }


}
