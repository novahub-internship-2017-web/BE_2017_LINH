package com.linhtran.springboot.booksmanagement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.request.BlockForm;
import com.linhtran.springboot.booksmanagement.response.BookDTO;
import com.linhtran.springboot.booksmanagement.service.BookService;
import com.linhtran.springboot.booksmanagement.service.UserService;
import com.linhtran.springboot.booksmanagement.validation.BookValidation;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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


        BookDTO bookDTO = new BookDTO();
        List<Book> result;
        List<Book> disabledList;

        if (isAdmin(request) && !isMyList) {//If admin want to see all list
            result = bookService.listAllBooks();
        }
        //If login user want to see list of books
        else if (authentication != null) {
            String userEmail = authentication.getName();
            User currentUser = userService.searchUserByEmail(userEmail);
            if (!isMyList) {//Want to see all books have permitted
                result = bookService.listAllBooksByStatus(true);
                disabledList = bookService.listAllBooksByUserIdAndStatus(currentUser.getId(), false);
                result.addAll(disabledList);
            } else {//Want to see their list
                result = bookService.listAllBooksByUserIdAndStatus(currentUser.getId(), true);
                disabledList = bookService.listAllBooksByUserIdAndStatus(currentUser.getId(), false);
                result.addAll(disabledList);
            }
        }
        //If anonymous user want to see book list
        else {
             result = bookService.listAllBooksByStatus(true);
        }

        logger.info(searchType);
        logger.info(searchValue);

        //For searching request
        result = bookService.searchBooks(searchType, searchValue, result);


        int amountOfBooks = result.size();
        bookService.sortBooks(result, type);
        result = bookService.pagingBooks(result, maxbooks, page);

        bookDTO.setResult(result);
        bookDTO.setAmountOfBooks(amountOfBooks);
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

//    @JsonView(Views.Public.class)
//    @PostMapping(value = "/books/search")
//    public BookDTO searchBooks(@RequestBody SearchBookForm search) {
//        BookDTO result = new BookDTO();
//        result.setResult(bookService.searchBooks(search.getSearchType(), search.getSearchValue()));
//        return result;
//    }

//    Not validate owner yet
    @JsonView(Views.Public.class)
    @PutMapping("/book/block")
    public Book blockUnblockBook(@RequestBody BlockForm blockBookForm,
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

    @JsonView(Views.Public.class)
    @DeleteMapping("/book/delete")
    public Book deleteBook(@RequestBody Book book,
                           Principal principal,
                           HttpServletRequest request) {
        if (principal != null) {
            User user = userService.searchUserByEmail(principal.getName());
            Book currentBook = bookService.searchBookById(book.getId());
            if (isAdmin(request) || user.getId() == currentBook.getUser().getId()) {
                bookService.deleteBook(currentBook);
            }
            return currentBook;
        }
        return new Book();
    }


    private boolean isAdmin(HttpServletRequest request) {
        SecurityContextHolderAwareRequestWrapper sc =
                new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
        return sc.isUserInRole("ADMIN");

    }

}
