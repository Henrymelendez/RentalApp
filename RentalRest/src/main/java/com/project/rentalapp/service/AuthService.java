package com.project.rentalapp.service;

import com.project.rentalapp.entities.User;

public interface AuthService {
		public User register(User user);
		public User getUserByUsername(String username);

	}


