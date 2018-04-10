package com.linhtran.springboot.booksmanagement.repository;

import com.linhtran.springboot.booksmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
}
