package com.linhtran.springboot.booksmanagement.repository;

import com.linhtran.springboot.booksmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);

    List<Book> findByEnabled(boolean enabled);

    List<Book> findByUserIdAndEnabled(int userId, boolean enabled);

    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorContaining(String author);
}
