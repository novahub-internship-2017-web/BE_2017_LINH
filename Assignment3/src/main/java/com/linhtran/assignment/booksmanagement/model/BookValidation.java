package com.linhtran.assignment.booksmanagement.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.assignment.booksmanagement.view.Views;

public class BookValidation {

    @JsonView(Views.Public.class)
    private Book book;

    @JsonView(Views.Public.class)
    private boolean validTitle;

    @JsonView(Views.Public.class)
    private boolean validAuthor;

    @JsonView(Views.Public.class)
    private boolean validBook;


    public BookValidation(Book book) {
        this.book = book;
        this.validTitle = book.getTitle().length() > 0;
        this.validAuthor = book.getAuthor().length() > 0;
        this.validBook = validAuthor && validTitle;
    }

    public boolean isValidTitle() {
        return validTitle;
    }

    public void setValidTitle(boolean validTitle) {
        this.validTitle = validTitle;
    }

    public boolean isValidAuthor() {
        return validAuthor;
    }

    public void setValidAuthor(boolean validAuthor) {
        this.validAuthor = validAuthor;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isValidBook() {
        return validBook;
    }

    public void setValidBook(boolean validBook) {
        this.validBook = validBook;
    }
}
