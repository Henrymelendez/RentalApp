package com.project.rentalapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.User;
import com.project.rentalapp.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public User showUser(int userId) {
		// TODO Auto-generated method stub
		
		return userRepo.findById(userId);
	}
	

	
	
	
	
	
	

}
