package com.linhtran.springboot.booksmanagement.service;

import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.BookRepository;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book searchBookById(int id) {
        return bookRepository.findById(id).orElse(null);
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
}
