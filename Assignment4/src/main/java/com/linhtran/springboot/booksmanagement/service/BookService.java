package com.linhtran.springboot.booksmanagement.service;

import com.linhtran.springboot.booksmanagement.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAllBooks();

    List<Book> searchBooks(String searchType, String searchValue);

    Book searchBookById(int id);

    Book searchBookByTitle(String title);

    void addNewBook(Book newBook);

    void updateBook(Book currentBook);
}
