package com.linhtran.springboot.booksmanagement.service;

import com.linhtran.springboot.booksmanagement.model.*;
import com.linhtran.springboot.booksmanagement.repository.BookRepository;
import com.linhtran.springboot.booksmanagement.repository.CommentRepository;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void postNewComment(Book book, User user, MainComment mainComment) {
        mainComment.setUserId(user.getId());
        mainComment.setBookId(book.getId());
        commentRepository.save(mainComment);
    }

    @Override
    public void replyComment(MainComment mainComment, ReplyComment replyComment) {

    }
}
