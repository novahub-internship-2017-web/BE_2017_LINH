package com.linhtran.assignment.booksmanagement.service;

import java.util.List;

import com.linhtran.assignment.booksmanagement.model.User;

public interface UserService {
	
	void register(User user);
	
	void setRoleToUser(User user, String role);
	 
	List<User> list();

	User searchUserByEmail(String email);

}
