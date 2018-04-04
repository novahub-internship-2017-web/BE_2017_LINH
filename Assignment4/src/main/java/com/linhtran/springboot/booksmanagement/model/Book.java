package com.linhtran.springboot.booksmanagement.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.view.Views;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {

    @JsonView(Views.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(Views.Public.class)
    @Column
    private String title;

    @JsonView(Views.Public.class)
    @Column
    private String author;

    @JsonView(Views.Public.class)
    @Column
    private String description;

    @JsonView(Views.Public.class)
    @Column
    private Date createdAt;

    @JsonView(Views.Public.class)
    @Column
    private Date updatedAt;

    @JsonView(Views.Public.class)
    @Column
    private String imageUrl;

    @JsonView(Views.Public.class)
    @Column
    private boolean enabled;

    @JsonView(Views.Public.class)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.enabled = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
