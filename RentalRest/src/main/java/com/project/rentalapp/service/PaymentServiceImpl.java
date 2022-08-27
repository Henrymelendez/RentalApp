package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Payment;
import com.project.rentalapp.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentRepository paymentRepo;

	@Override
	public Payment showPayment(int id) {
		// TODO Auto-generated method stub
		return paymentRepo.findById(id);
	}

	@Override
	public Payment addPayment(Payment payment) {
		// TODO Auto-generated method stub
		
		
		
		return paymentRepo.saveAndFlush(payment);
	}

	@Override
	public Payment updatePayment(Payment payment, int id) {
		
		Payment managed = paymentRepo.findById(id);
		
		if(managed != null) {
			
			managed.setAmount(payment.getAmount());
			managed.setModeOfPayment(payment.getModeOfPayment());
			managed.setPaymentDate(payment.getPaymentDate());
			managed.setPaymentNotes(payment.getPaymentNotes());
			managed.setProperty(payment.getProperty());
			managed.setTenant(payment.getTenant());
			
		}
		
		return managed;
	}

	@Override
	public boolean destroyPayment(int id) {
		
		paymentRepo.deleteById(id);
		
		boolean deleted = !paymentRepo.existsById(id);
		
		return deleted;
	}

	@Override
	public List<Payment> index() {
		// TODO Auto-generated method stub
		return paymentRepo.findAll();
	}

}
