package com.project.rentalapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
	
	public Property findById(int id);

	public List<Property> findByUser_Id(int id);
}
