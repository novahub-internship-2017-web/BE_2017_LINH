package com.linhtran.springboot.booksmanagement.repository;

import com.linhtran.springboot.booksmanagement.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    List<Comment> findByBookId(int id);
}
