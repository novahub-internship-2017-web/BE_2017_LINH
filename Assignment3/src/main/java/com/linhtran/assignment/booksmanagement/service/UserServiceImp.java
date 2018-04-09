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
		user.setEnabled(true);
		userDao.save(user);
	}

	@Override
	public void deleteUser(int id) {
		User user = userDao.findUserById(id);
		userDao.deleteUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public User searchUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public User searchUserById(int id) {
		return userDao.findUserById(id);
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public void setRoleToUser(User user, String role) {
		user.setRole(role);
	}

	@Override
	public int changeUserPassword(User user, String currentPass,
									 String newPass, String confirmNewPass) {

		if (!passwordEncoder.matches(currentPass, user.getPassword())) {
			return 0;
		}
		if (!newPass.equals(confirmNewPass)) {
			return 1;
		}
		user.setPassword(passwordEncoder.encode(newPass));
		return 2;
	}
}
