package com.project.rentalapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rentalapp.entities.Property;
import com.project.rentalapp.entities.User;
import com.project.rentalapp.service.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("user/{userId}")
	public User getUser(@PathVariable Integer userId, HttpServletResponse res) {	
		
		 User user = userService.showUser(userId);
		  if (user == null) {
		    res.setStatus(404);
		  }
		  return user;
	}
	
	
	@DeleteMapping("user/{userId}")
	public void deleteUser(@PathVariable int userId, HttpServletResponse res) {
		
		try {
			
			if(userService.destroyUser(userId)) {
				res.setStatus(201);
			}
			else {
				res.setStatus(404);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			
		}
		
	}
	
	
	@GetMapping("user")
	public List<User> listOfUsers(HttpServletResponse res, HttpServletRequest req, Principal principal){
		try {
			List<User> users = userService.userIndex();
			
			if(users == null) {
				res.setStatus(404);
			}
			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
			
		}
	}
	
	
	// FIX ME RETURNS NULL 
	@GetMapping("user/{userId}/properties")
	public List<Property> getUserProperties(@PathVariable int userId, HttpServletRequest req, HttpServletResponse res, Principal principal){
		
		try {
			List<Property> properties = userService.getUserProperties(userId);
			
			if(properties == null) {
				res.setStatus(404);
			}
			return properties;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
		
	}

	
	

}
