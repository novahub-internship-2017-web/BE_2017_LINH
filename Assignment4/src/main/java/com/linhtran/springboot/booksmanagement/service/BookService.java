package com.linhtran.springboot.booksmanagement.service;

import com.linhtran.springboot.booksmanagement.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAllBooks();

    List<Book> listAllBooksByStatus(boolean enabled);

    List<Book> listAllBooksByUserIdAndStatus(int userId, boolean enabled);

    List<Book> pagingBooks(List<Book> books, int maxBooksPerPage, int page);

    List<Book> searchBooks(String searchValue, List<Book> books);

    List<Book> sortBooks(List<Book> books, String sortType);

    Book searchBookById(int id);

    Book searchBookByTitle(String title);

    Book addNewBook(Book newBook);

    void updateBook(Book currentBook);

    void deleteBook(Book book);

}
