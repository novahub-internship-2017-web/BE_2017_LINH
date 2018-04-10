package com.linhtran.springboot.booksmanagement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.Comment;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.response.CommentDTO;
import com.linhtran.springboot.booksmanagement.service.BookService;
import com.linhtran.springboot.booksmanagement.service.CommentService;
import com.linhtran.springboot.booksmanagement.service.UserService;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    BookService bookService;

    @JsonView(Views.Public.class)
    @GetMapping("/all-comments/{id}")
    public CommentDTO getAllComments(@PathVariable("id") int bookId) {
        List<Comment> comments = commentService.searchCommentsByBookId(bookId);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setResult(comments);

        return commentDTO;
    }

    @JsonView(Views.Public.class)
    @PostMapping("/post-comment")
    public CommentDTO postNewComment(@RequestBody Comment comment, Principal principal) {
        String userEmail = principal.getName();
        User user = userService.searchUserByEmail(userEmail);
        Book book = bookService.searchBookById(comment.getBookId());
        Comment newComment = new Comment(comment.getContent());
        CommentDTO commentDTO = new CommentDTO();

        if (!comment.getContent().trim().isEmpty()) {
            commentService.postNewComment(book, user, newComment);
            commentDTO.getResult().add(newComment);
        }

        return commentDTO;
    }

}
