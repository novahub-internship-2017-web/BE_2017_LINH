package com.linhtran.springboot.booksmanagement.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.Comment;
import com.linhtran.springboot.booksmanagement.view.Views;

import java.util.ArrayList;
import java.util.List;

public class CommentDTO {

    @JsonView(Views.Public.class)
    private List<Comment> result = new ArrayList<>();

    public List<Comment> getResult() {
        return result;
    }

    public void setResult(List<Comment> result) {
        this.result = result;
    }
}
