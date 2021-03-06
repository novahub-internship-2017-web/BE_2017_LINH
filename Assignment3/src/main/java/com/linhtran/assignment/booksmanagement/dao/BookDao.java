package com.linhtran.assignment.booksmanagement.dao;

import com.linhtran.assignment.booksmanagement.model.Book;


import java.util.List;

public interface BookDao {
    List<Book> list();

    Book findBookByID(int id);

    void updateBook(Book book);

    void deleteBook(Book book);

    List<Book> searchBookByTitle(String title);

    List<Book> searchBookByAuthor(String author);

}
