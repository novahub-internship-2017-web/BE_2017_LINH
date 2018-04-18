package com.linhtran.springboot.booksmanagement.response;





import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.Book;
import com.linhtran.springboot.booksmanagement.view.Views;

import java.util.List;

public class BookDTO {

    @JsonView(Views.Public.class)
    private List<Book> result;

    @JsonView(Views.Public.class)
    private int amountOfBooks;


    public List<Book> getResult() {
        return result;
    }

    public void setResult(List<Book> result) {
        this.result = result;
    }

    public int getAmountOfBooks() {
        return amountOfBooks;
    }

    public void setAmountOfBooks(int amountOfBooks) {
        this.amountOfBooks = amountOfBooks;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "result=" + result +
                '}';
    }
}
