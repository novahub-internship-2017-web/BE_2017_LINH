package com.linhtran.assignment.booksmanagement.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.assignment.booksmanagement.model.AjaxRespondBodyBook;
import com.linhtran.assignment.booksmanagement.model.Book;
import com.linhtran.assignment.booksmanagement.model.SearchBookForm;
import com.linhtran.assignment.booksmanagement.service.BookService;
import com.linhtran.assignment.booksmanagement.view.Views;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BookControllerRest {

    @Autowired
    BookService bookService;

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/rest/books")
    public AjaxRespondBodyBook getAllBooks() {
        AjaxRespondBodyBook result = new AjaxRespondBodyBook();
        result.setResult(bookService.listAllBook());
        return result;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/rest/search", method = RequestMethod.POST)
    public AjaxRespondBodyBook searchBook(@RequestBody SearchBookForm search) {
        AjaxRespondBodyBook result = new AjaxRespondBodyBook();
        result.setResult(bookService.searchBooks(search.getSearchType(), search.getSearchValue()));
        return result;
    }


}