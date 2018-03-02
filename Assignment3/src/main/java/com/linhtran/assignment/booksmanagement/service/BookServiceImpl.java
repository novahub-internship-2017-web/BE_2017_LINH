package com.linhtran.assignment.booksmanagement.service;

import com.linhtran.assignment.booksmanagement.dao.BookDao;
import com.linhtran.assignment.booksmanagement.dao.UserDao;
import com.linhtran.assignment.booksmanagement.model.Book;
import com.linhtran.assignment.booksmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    UserDao userDao;

    @Autowired
    BookDao bookDao;


    @Override
    public void addBooks(Book newBook) {
        String currentUserEmail=  SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User currentUser = userDao.findUserByEmail(currentUserEmail);
        newBook.setUser(currentUser);
        currentUser.getBooks().add(newBook);
        userDao.update(currentUser);
    }

    @Override
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }

    @Override
    public void updateBookInformation(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public List<Book> listAllBook() {
        return bookDao.list();
    }

    @Override
    public Book searchBookById(int id) {
        return bookDao.findBookByID(id);
    }

    @Override
    public List<Book> searchBooks(String searchType, String searchValue) {
        switch (searchType) {
            case "by-title":
                return bookDao.searchBookByTitle(searchValue);
            case "by-author":
                return bookDao.searchBookByAuthor(searchValue);
            default:
                return new ArrayList<>();
        }

    }
}
