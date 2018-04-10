package com.linhtran.springboot.booksmanagement.service;



import com.linhtran.springboot.booksmanagement.model.User;

import java.util.List;

public interface UserService {
	
	void register(User user);
//
//	void setRoleToUser(User user, String role);
//
	List<User> listAll();
//
	User searchUserByEmail(String email);
//
//	User searchUserById(int id);
//
//	void deleteUser(int id);
//
//	void updateUser(User user);
//
	int changeUserPassword(User user, String currentPass, String newPass, String confirmNewPass);

}
