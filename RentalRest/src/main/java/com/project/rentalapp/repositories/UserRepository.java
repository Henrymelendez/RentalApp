package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findById(int userId);
	User findByUserName(String username);

	

}
