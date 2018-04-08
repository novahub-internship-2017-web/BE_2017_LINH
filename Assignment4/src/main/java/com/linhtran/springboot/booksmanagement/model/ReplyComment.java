package com.linhtran.springboot.booksmanagement.model;

import javax.persistence.*;


public class ReplyComment extends Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private MainComment mainComment;

    public ReplyComment(String message) {
        super(message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MainComment getMainComment() {
        return mainComment;
    }

    public void setMainComment(MainComment mainComment) {
        this.mainComment = mainComment;
    }
}
