package com.project.rentalapp.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rentalapp.entities.User;
import com.project.rentalapp.service.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("user/{userId}")
	public User getUserForTest(@PathVariable Integer userId, HttpServletResponse res) {	
		
		 User user = userService.showUser(userId);
		  if (user == null) {
		    res.setStatus(404);
		  }
		  return user;
	}
	
	
	
	
	


}
