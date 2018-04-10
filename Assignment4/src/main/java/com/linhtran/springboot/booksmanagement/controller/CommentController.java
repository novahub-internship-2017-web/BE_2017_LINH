package com.linhtran.springboot.booksmanagement.controller;

import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.MainComment;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.service.BookService;
import com.linhtran.springboot.booksmanagement.service.CommentService;
import com.linhtran.springboot.booksmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    BookService bookService;

    @PostMapping("/post-comment")
    public MainComment postNewComment(@RequestBody MainComment comment, Principal principal) {
        String userEmail = principal.getName();
        User user = userService.searchUserByEmail(userEmail);
        Book book = bookService.searchBookById(comment.getBookId());
        MainComment newComment = new MainComment(comment.getContent());
        commentService.postNewComment(book, user, newComment);
        return newComment;
    }

}
