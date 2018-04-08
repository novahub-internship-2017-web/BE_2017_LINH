package com.linhtran.springboot.booksmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class MainComment extends Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int bookId;

    public MainComment() {
    }

    public MainComment(String message) {
        super(message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
