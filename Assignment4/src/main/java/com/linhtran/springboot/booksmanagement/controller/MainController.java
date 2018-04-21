package com.linhtran.springboot.booksmanagement.controller;

import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import com.linhtran.springboot.booksmanagement.service.BookService;
import com.linhtran.springboot.booksmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;


    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal == null) {
            model.addAttribute("user", new User());
            return "home";
        } else {
            return "redirect:/login-success";
        }
    }

    @GetMapping("/login-success")
    public String loginSucess(HttpSession session, Authentication authentication, Model model) {
        String userEmail = authentication.getName();
        User user = userService.searchUserByEmail(userEmail);
        if (!user.isEnabled()) {
            model.addAttribute("enabledUser", false);
            model.addAttribute("user", new User());
            session.invalidate();
            return "home";
        }
        return "redirect:/dashboard";
    }


    @GetMapping("/login-failure")
    public String loginFailure(Model model) {
        model.addAttribute("loginFailured", true);
        model.addAttribute("user", new User());
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        if (principal != null) {
            String loginUserEmail = principal.getName();
            User loginUser = userService.searchUserByEmail(loginUserEmail);
            model.addAttribute("user", loginUser);
        } else {
            model.addAttribute("user", new User());
        }

        return "dashboard";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
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

    @GetMapping("/book/detail/{id}")
    public String bookDetail(@PathVariable("id") int bookId, Model model) {
        Book book = bookService.searchBookById(bookId);
        model.addAttribute("book", book);
        return "book-detail";
    }


}
