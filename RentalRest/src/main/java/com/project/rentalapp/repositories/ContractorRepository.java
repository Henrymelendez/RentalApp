package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
	
	public Contractor findById(int id);

}
