package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.Lease;

public interface LeaseRepositroy extends JpaRepository<Lease, Integer> {
	
	public Lease findById(int id);

}
