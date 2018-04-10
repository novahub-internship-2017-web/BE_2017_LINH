package com.linhtran.springboot.booksmanagement.model;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class Comment {

    private String content;

    private Date createdAt;

    private Date updateAt;

    private int likes;

    private int userId;

    public Comment() {
    }

    public Comment(String content) {
        this.content = content;
        this.createdAt = new Date();
        this.updateAt = createdAt;
        this.likes = 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
