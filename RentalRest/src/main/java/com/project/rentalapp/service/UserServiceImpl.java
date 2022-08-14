package com.project.rentalapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Property;
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

	@Override
	public User addUser(User user) {
		
		
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User updateUser(User user, int id) {
		
		User mangaed = userRepo.findById(id);
		
		if(mangaed != null) {
			
			mangaed.setEnabled(user.isEnabled());
			mangaed.setPassword(user.getPassword());
			mangaed.setProperties(user.getProperties());
			mangaed.setRole(user.getRole());
			mangaed.setUserName(user.getUserName());
			
		}
		
		return mangaed;
	}

	@Override
	public boolean destroyUser(int id) {
		userRepo.deleteById(id);
		boolean deleted = !userRepo.existsById(id);
		return deleted;
	}

	@Override
	public List<User> userIndex() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	
	// FIXME TODO get PROPERTIES
	@Override
	public List<Property> getUserProperties(int id) {
		
		if(!userRepo.existsById(id)) {
			return null;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	

}
