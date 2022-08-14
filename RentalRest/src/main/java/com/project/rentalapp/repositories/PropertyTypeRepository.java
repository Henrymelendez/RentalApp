package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.PropertyType;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, Integer> {
	
	
	public PropertyType findById(int id);
	
}
