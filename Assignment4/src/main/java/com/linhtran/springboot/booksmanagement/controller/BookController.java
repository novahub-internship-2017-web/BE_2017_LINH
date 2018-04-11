package com.linhtran.springboot.booksmanagement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.request.SearchBookForm;
import com.linhtran.springboot.booksmanagement.response.BookDTO;
import com.linhtran.springboot.booksmanagement.service.BookService;
import com.linhtran.springboot.booksmanagement.validation.BookValidation;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api")
public class BookController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    @JsonView(Views.Public.class)
    @GetMapping(value = "/books")
    public BookDTO listAllBooks(Principal principal) {
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
        logger.info(book.getDescription());
        if (bookValidation.isValidBook()) {
            bookService.addNewBook(newBook);
            newBook = bookService.searchBookByTitle(newBook.getTitle());
            bookValidation.getBook().setId(newBook.getId());
            logger.info("" + newBook.getId());
        }
        return bookValidation;
    }

    @JsonView(Views.Public.class)
    @PostMapping("/modify-book")
    public BookValidation modifyBookInformation(@RequestBody Book book) {
        Book newBook = new Book(book.getTitle(), book.getAuthor());
        newBook.setImageUrl("/resources/upload/book-covers/genericBookCover.jpg");
        newBook.setDescription(book.getDescription());
        BookValidation bookValidation = new BookValidation(newBook);
        if (bookValidation.isValidBook()) {
            bookService.updateBook(book);
        }
        return bookValidation;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/get-book/{id}")
    public Book getBookById(@PathVariable("id") int bookId) {
        return bookService.searchBookById(bookId);
    }

    @JsonView(Views.Public.class)
    @PostMapping(value = "/books/search")
    public BookDTO searchBooks(@RequestBody SearchBookForm search) {
        BookDTO result = new BookDTO();
        result.setResult(bookService.searchBooks(search.getSearchType(), search.getSearchValue()));
        return result;
    }

}
