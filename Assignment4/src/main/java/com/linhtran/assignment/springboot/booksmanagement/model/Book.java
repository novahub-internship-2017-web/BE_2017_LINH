package com.linhtran.assignment.springboot.booksmanagement.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.assignment.springboot.booksmanagement.view.Views;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book implements Serializable{

	@JsonView(Views.Public.class)
	private int id;

	@JsonView(Views.Public.class)
	@Column(name = "title")
	@NotEmpty
	private String title;

	@JsonView(Views.Public.class)
	@Column(name = "author")
	@NotEmpty
	private String author;

	@JsonView(Views.Public.class)
	@Column(name = "createdAt")
	private Date createdAt;

	@JsonView(Views.Public.class)
	@Column(name = "updatedAt")
	private Date updatedAt;

	@JsonView(Views.Public.class)
	@Column(name = "description")
	private String description;

	@JsonView(Views.Public.class)
	@Column(name = "imagePath")
	private String imagePath;

	@JsonView(Views.Public.class)
	private User user;

	public Book() {}

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.createdAt = new Date();
		this.updatedAt = createdAt;
	}

	@Id
	@GeneratedValue
	@Column(name = "book_id")
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
