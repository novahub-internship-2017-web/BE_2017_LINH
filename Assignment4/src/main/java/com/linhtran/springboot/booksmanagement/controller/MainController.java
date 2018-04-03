package com.linhtran.springboot.booksmanagement.controller;

import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Principal principal) {
        if (principal == null) {
            return "home";
        } else {
            return "redirect:/login-success";
        }
    }

    @GetMapping("/login-success")
    public String loginSucess(HttpSession session, Authentication authentication, Model model) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail);
        if (!user.isEnabled()) {
            model.addAttribute("enabledUser", false);
            session.invalidate();
            return "home";
        }
        return "redirect:/api/users";
    }

    @GetMapping("/login-failure")
    public String loginFailure(Model model) {
        model.addAttribute("loginFailured", true);
        return "home";
    }

}
