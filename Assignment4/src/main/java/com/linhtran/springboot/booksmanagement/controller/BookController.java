package com.linhtran.springboot.booksmanagement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.response.BookDTO;
import com.linhtran.springboot.booksmanagement.service.BookService;
import com.linhtran.springboot.booksmanagement.validation.BookValidation;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @JsonView(Views.Public.class)
    @GetMapping(value = "/books")
    public BookDTO listAllBooks() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setResult(bookService.listAllBooks());
        return bookDTO;
    }

    @JsonView(Views.Public.class)
    @PostMapping("/add-book")
    public BookValidation addBook(@RequestBody Book book) {
        Book newBook = new Book(book.getTitle(), book.getAuthor());
        newBook.setImageUrl("/resources/upload/book-covers/genericBookCover.jpg");
        newBook.setDescription(book.getDescription());
        BookValidation bookValidation = new BookValidation(newBook);
        if (bookValidation.isValidBook()) {
            bookService.addNewBook(newBook);
        }
        return bookValidation;
    }

}
