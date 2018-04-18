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
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping(value = "/books/list")
    public BookDTO listAllBooks(HttpServletRequest request,
                                Authentication authentication,
                                @RequestParam("type") String type,
                                @RequestParam("max-books") int maxbooks,
                                @RequestParam("page") int page,
                                @RequestParam("search-type") String searchType,
                                @RequestParam("search-value") String searchValue,
                                @RequestParam("my-list") boolean isMyList) {
        String userEmail = authentication.getName();
        User currentUser = userService.searchUserByEmail(userEmail);

        BookDTO bookDTO = new BookDTO();
        List<Book> result;
        List<Book> disabledList;

        if (isAdmin(request) && !isMyList) {
            result = bookService.listAllBooks();
        } else if (isMyList) {
            result = bookService.listAllBooksByUserIdAndStatus(currentUser.getId(), true);
            disabledList = bookService.listAllBooksByUserIdAndStatus(currentUser.getId(), false);
            result.addAll(disabledList);
        } else {
            result = bookService.listAllBooksByStatus(true);
            disabledList = bookService.listAllBooksByUserIdAndStatus(currentUser.getId(), false);
            result.addAll(disabledList);
        }



        //For searching request
        result = bookService.searchBooks(searchType, searchValue, result);
        int amountOfBooks = result.size();
        bookService.sortBooks(result, type);
        result = bookService.pagingBooks(result, maxbooks, page);

        bookDTO.setResult(result);
        bookDTO.setAmountOfBooks(amountOfBooks);
        return bookDTO;
    }

//    @JsonView(Views.Public.class)
//    @GetMapping(value = "/books/sort")
//    public BookDTO sortBooks(@RequestParam("type") String type,
//                             @RequestParam("page") int page,
//                             @RequestParam("max-books") int maxBooks,
//                             HttpServletRequest request,
//                             Authentication authentication) {
//        BookDTO bookDTO = listAllBooks(request, authentication, maxBooks, page);
//        bookService.sortBooks(bookDTO.getResult(), type);
//        return bookDTO;
//    }

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

//    @JsonView(Views.Public.class)
//    @PostMapping(value = "/books/search")
//    public BookDTO searchBooks(@RequestBody SearchBookForm search) {
//        BookDTO result = new BookDTO();
//        result.setResult(bookService.searchBooks(search.getSearchType(), search.getSearchValue()));
//        return result;
//    }

//    Not validate owner yet
    @JsonView(Views.Public.class)
    @PostMapping("/book/block")
    public Book blockUnblockBook(@RequestBody BlockBookForm blockBookForm,
                                 Authentication authentication,
                                 HttpServletRequest request) {
        Book book = bookService.searchBookById(blockBookForm.getId());
        book.setEnabled(blockBookForm.isEnabled());
        //Check if owner are modify book's information
        logger.info("======>" + isAdmin(request));
        if (isAdmin(request)) {
            bookService.updateBook(book);
        }
        return book;
    }

    private boolean isAdmin(HttpServletRequest request) {
        SecurityContextHolderAwareRequestWrapper sc =
                new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
        return sc.isUserInRole("ADMIN");

    }

}
