package com.linhtran.springboot.booksmanagement.service;

import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.BookRepository;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String searchType, String searchValue) {
        switch (searchType) {
            case "by-title":
                return bookRepository.findByTitleContaining(searchValue);
            case "by-author":
                return bookRepository.findByAuthorContaining(searchValue);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<Book> sortBooks(List<Book> books, String sortType) {
        for (int i = 0; i < books.size() - 1; i++ ) {
            for (int j = 0; j < books.size(); j++) {
                if (books.get(i).compareTo(books.get(j), sortType) < 0) {
                    Book temp = books.get(i);
                    books.set(i, books.get(j));
                    books.set(j, temp);
                }
            }
        }

       return books;
    }

    @Override
    public Book searchBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book searchBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public void addNewBook(Book newBook) {
        String currentUserEmail=  SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User currentUser = userRepository.findByEmail(currentUserEmail);
        newBook.setUser(currentUser);
        currentUser.getBooks().add(newBook);
        userRepository.save(currentUser);
    }

    @Override
    public void updateBook(Book currentBook) {
        Book book = bookRepository.findById(currentBook.getId()).orElse(null);
        logger.info("Current book =====>" + currentBook.toString());
        if (book == null) {
            logger.info("Object book is null");
        } else {
            logger.info("==========>" + book.toString());
        }
        if (book != null) {
            book.setTitle(currentBook.getTitle());
            book.setAuthor(currentBook.getAuthor());
            if (currentBook.getDescription() != null && !currentBook.getDescription().trim().isEmpty()) {
                book.setDescription(currentBook.getDescription());
            }
            bookRepository.save(book);
        }
    }
}
