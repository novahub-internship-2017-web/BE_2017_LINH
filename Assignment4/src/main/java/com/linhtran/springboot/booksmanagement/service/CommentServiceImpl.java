package com.linhtran.springboot.booksmanagement.service;

import com.linhtran.springboot.booksmanagement.model.*;
import com.linhtran.springboot.booksmanagement.repository.BookRepository;
import com.linhtran.springboot.booksmanagement.repository.CommentRepository;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void postNewComment(Book book, User user, Comment newComment) {
        newComment.setBookId(book.getId());
        newComment.setUser(user);
        user.getComments().add(newComment);
        userRepository.save(user);
    }

    @Override
    public List<Comment> searchCommentsByBookId(int id) {
        return commentRepository.findByBookId(id);
    }

}
