package com.linhtran.assignment.booksmanagement.controller;

import com.linhtran.assignment.booksmanagement.model.Book;
import com.linhtran.assignment.booksmanagement.model.User;
import com.linhtran.assignment.booksmanagement.service.BookService;
import com.linhtran.assignment.booksmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    private String bookDetail = "book-detail";
    private String bookModify = "book-modify";
    private String booksAttribute = "books";

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/book/add")
    public String addBook(Model model,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("description") String description) {
        Book newBook = new Book(title, author);
        newBook.setDescription(description);
        if (!title.trim().isEmpty() && !author.trim().isEmpty()) {
            bookService.addBooks(newBook);
            model.addAttribute("isSucceed", true);
        } else {
            if (title.trim().isEmpty()) {
                model.addAttribute("emptyTitle", true);
            }
            if(author.trim().isEmpty()) {
                model.addAttribute("emptyAuthor", true);
            }
        }

        return "addbook";
    }

    @GetMapping("/book/add")
    public String addBook() {
        return "addbook";
    }

    @GetMapping("/book/list")
    public String listBook(Model model) {
        model.addAttribute(booksAttribute, bookService.listAllBook());
        return "bookslist";
    }

    @GetMapping("/book/mybook")
    public String myBook(Model model, Principal principal) {
        String email = principal.getName();
        User currentUser = userService.searchUserByEmail(email);
        model.addAttribute(booksAttribute, currentUser.getBooks());
        return "my-book";
    }

    @GetMapping("/book/detail")
    public String bookDetail(
            Model model,
            @RequestParam("id") int id ){
        Book book = bookService.searchBookById(id);
        model.addAttribute("book", book);
        return bookDetail;
    }

    @GetMapping("/book/modify")
    public String modifyBook(
            Model model,
            @RequestParam("id") int id, Principal principal){
        Book book = bookService.searchBookById(id);
        model.addAttribute("book", book);
        if (principal.getName().equals(book.getUser().getEmail())) {
            return bookModify;
        } else {
            model.addAttribute("permission", false);
            return bookDetail;
        }
    }

    @GetMapping("/book/delete")
    public String deleteBook(
            Model model,
            @RequestParam("id") int id, Principal principal){
        Book book = bookService.searchBookById(id);
        if (principal.getName().equals(book.getUser().getEmail())) {
            bookService.deleteBook(book);
            return "redirect:/book/mybook";
        } else {
            model.addAttribute("permission", false);
            return bookDetail;
        }
    }

    @PostMapping("/book/modify")
    public String bookModify(@ModelAttribute("book") @Valid Book book,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return bookModify;
        }
        Book oldBook = bookService.searchBookById(book.getId());
        Date updatedAt = new Date();

        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setUpdatedAt(updatedAt);
        oldBook.setDescription(book.getDescription());
        bookService.updateBookInformation(oldBook);
        model.addAttribute("updated", true);
        model.addAttribute("book", oldBook);
        return bookModify;
    }

    @PostMapping("/book/search")
    public String searchBook(
            Model model,
            @RequestParam("search-type") String searchType,
            @RequestParam("search-value") String searchValue) {
        List<Book> books = bookService.searchBooks(searchType, searchValue);
        model.addAttribute(booksAttribute, books);
        if (books.isEmpty()) {
            model.addAttribute("noResult", true);
        }
        return "bookslist";

    }

}
