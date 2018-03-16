package com.linhtran.assignment.booksmanagement.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.assignment.booksmanagement.view.Views;

import java.util.List;

public class AjaxRespondBodyBook {

    @JsonView(Views.Public.class)
    private List<Book> result;


    public List<Book> getResult() {
        return result;
    }

    public void setResult(List<Book> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxRespondBodyBook{" +
                "result=" + result +
                '}';
    }
}
