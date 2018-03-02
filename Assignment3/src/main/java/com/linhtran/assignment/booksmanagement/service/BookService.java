package com.linhtran.assignment.booksmanagement.service;

import com.linhtran.assignment.booksmanagement.model.Book;

import java.util.List;

public interface BookService {
    void addBooks(Book newBook);

    void deleteBook(Book book);

    void updateBookInformation(Book book);

    List<Book> listAllBook();

    Book searchBookById(int id);

    List<Book> searchBooks(String searchType, String searchValue);
}
