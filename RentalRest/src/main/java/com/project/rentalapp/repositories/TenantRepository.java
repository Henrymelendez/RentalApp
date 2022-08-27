package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
	
	Tenant findById(int id);

}
