package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
	
	Maintenance findById(int id);

}
