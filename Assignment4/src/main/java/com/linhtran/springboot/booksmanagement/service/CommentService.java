package com.linhtran.springboot.booksmanagement.service;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.MainComment;
import com.linhtran.springboot.booksmanagement.model.ReplyComment;
import com.linhtran.springboot.booksmanagement.model.User;

public interface CommentService {

    void postNewComment(Book book, User user, MainComment mainComment);

    void replyComment(MainComment mainComment, ReplyComment replyComment);
}
