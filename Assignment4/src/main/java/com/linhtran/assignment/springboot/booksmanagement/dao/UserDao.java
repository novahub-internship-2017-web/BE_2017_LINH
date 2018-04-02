package com.linhtran.assignment.springboot.booksmanagement.dao;

import com.linhtran.assignment.springboot.booksmanagement.model.User;

import java.util.List;

public interface UserDao {
	
	void save(User user);

	void update(User user);

	User findUserByEmail(String email);

	void deleteUser(User user);

	User findUserById(int id);

	List<User> list();	

}
