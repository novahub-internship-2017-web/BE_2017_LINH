package com.linhtran.assignment.booksmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

	private int id;
	
	@Column(name = "firstName")
	@Size(max = 20, min = 3, message = "{user.firstname.invalid}")
	private String firstName;
	
	@Column(name = "lastName")
	@Size(max = 20, min = 3, message = "{user.lastname.invalid}")
	private String lastName;
	
	
	@Column(name = "email")
	@Email(message = "{user.email.invalid}")
	private String email;
	
	@Column(name = "password")
	@Size(min = 8, message = "{user.password.invalid}")
	private String password;

	private String confirmPassword;
	
	@Column(name = "role")
	private String role;

	private Set<Book> books = new HashSet<>();

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
