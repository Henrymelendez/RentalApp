package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Payment;

public interface PaymentService {

	
	public Payment showPayment(int id);
	public Payment addPayment(Payment payment);
	public Payment updatePayment(Payment payment, int id);
	public boolean destroyPayment(int id);
	public List<Payment> index();
	
}
