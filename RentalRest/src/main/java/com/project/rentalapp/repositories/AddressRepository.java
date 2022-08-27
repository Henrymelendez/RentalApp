package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	public Address findById(int id); 

}
