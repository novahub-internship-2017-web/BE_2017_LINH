package com.linhtran.springboot.booksmanagement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.request.BlockBookForm;
import com.linhtran.springboot.booksmanagement.request.SearchBookForm;
import com.linhtran.springboot.booksmanagement.response.BookDTO;
import com.linhtran.springboot.booksmanagement.service.BookService;
import com.linhtran.springboot.booksmanagement.service.UserService;
import com.linhtran.springboot.booksmanagement.validation.BookValidation;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @JsonView(Views.Public.class)
    @GetMapping(value = "/books")
    public BookDTO listAllBooks(Authentication authentication) {
        Collection<? extends GrantedAuthority> granted = authentication.getAuthorities();
        String userEmail = authentication.getName();
        User currentUser = userService.searchUserByEmail(userEmail);
        boolean isAdmin = granted.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        BookDTO bookDTO = new BookDTO();
        List<Book> result;
        List<Book> disabledList;

        if (isAdmin) {
            result = bookService.listAllBooks();
        } else {
            result = bookService.listAllBooksByStatus(true);
            disabledList = bookService.listAllBooksByUserIdAndStatus(currentUser.getId(), false);
            result.addAll(disabledList);
        }

        bookDTO.setResult(result);
        return bookDTO;
    }

    @JsonView(Views.Public.class)
    @GetMapping(value = "/books/{type}")
    public BookDTO sortBooks(@PathVariable("type") String type) {
        BookDTO bookDTO = new BookDTO();
        List<Book> books = bookService.sortBooks(bookService.listAllBooks(), type);
        bookDTO.setResult(books);
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

//    Not validate owner yet
    @JsonView(Views.Public.class)
    @PostMapping("/book/block")
    public Book blockUnblockBook(@RequestBody BlockBookForm blockBookForm, Principal principal) {
        Book book = bookService.searchBookById(blockBookForm.getId());
        book.setEnabled(blockBookForm.isEnabled());
        //Check if owner are modify book's information
        if (book.getUser().getEmail().equals(principal.getName())) {
            bookService.updateBook(book);
        }
        return book;
    }

}
