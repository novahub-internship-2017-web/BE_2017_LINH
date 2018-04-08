package com.linhtran.springboot.booksmanagement.service;

import com.linhtran.springboot.booksmanagement.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAllBooks();

    Book searchBookById(int id);

    void addNewBook(Book newBook);
}
