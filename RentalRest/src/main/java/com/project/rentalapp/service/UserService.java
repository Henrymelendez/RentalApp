package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Property;
import com.project.rentalapp.entities.User;

public interface UserService {
	
	
	public User showUser(int userId);
	
	public User addUser(User user);
	
	public User updateUser(User user, int id);
	
	public boolean destroyUser(int id);
	
	public List<User> userIndex();
	
	public List<Property> getUserProperties(int id);
	
	
}
