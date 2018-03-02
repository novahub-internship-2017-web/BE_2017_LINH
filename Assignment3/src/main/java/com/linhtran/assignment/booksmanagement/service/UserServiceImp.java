package com.linhtran.assignment.booksmanagement.service;

import java.util.List;

import com.linhtran.assignment.booksmanagement.dao.UserDao;
import com.linhtran.assignment.booksmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void register(User user) {
		//Set role to user	
		setRoleToUser(user, "USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);		
	}

	@Override
	public User searchUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public void setRoleToUser(User user, String role) {
		user.setRole(role);
	}

}
