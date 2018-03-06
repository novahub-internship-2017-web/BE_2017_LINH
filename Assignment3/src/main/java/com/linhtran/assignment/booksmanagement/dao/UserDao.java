package com.linhtran.assignment.booksmanagement.dao;

import java.util.List;

import com.linhtran.assignment.booksmanagement.model.User;

public interface UserDao {
	
	void save(User user);

	void update(User user);

	User findUserByEmail(String email);

	void deleteUser(User user);

	User findUserById(int id);

	List<User> list();	

}
