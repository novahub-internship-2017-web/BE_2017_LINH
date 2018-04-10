package com.linhtran.springboot.booksmanagement.service;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.Comment;
import com.linhtran.springboot.booksmanagement.model.User;

import java.util.List;

public interface CommentService {

    void postNewComment(Book book, User user, Comment mainComment);

    List<Comment> searchCommentsByBookId(int id);


}
