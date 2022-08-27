package com.project.rentalapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rentalapp.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	public Payment findById(int id);
}
