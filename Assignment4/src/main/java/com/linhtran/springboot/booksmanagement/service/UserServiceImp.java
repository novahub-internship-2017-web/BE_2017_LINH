package com.linhtran.springboot.booksmanagement.service;


import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.RoleRepository;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void register(User user) {
		//Set role to user	
		user.setRoleId(2);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setAvatarUrl("/resources/img/default-avatar.jpg");
		userRepository.save(user);
	}

	@Override
	public User searchUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User searchUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void update(User user) {
		userRepository.save(user);	}



	@Override
	public List<User> listAll() {
		return userRepository.findAll();
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
