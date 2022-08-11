package com.project.rentalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.User;
import com.project.rentalapp.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW); 
		user.setEnabled(true);
		user.setRole("standard");
		userRepo.saveAndFlush(user);
		
		return user;

	}

	@Override
	public User getUserByUsername(String username) {
		
		return userRepo.findByUserName(username);
	}


}
