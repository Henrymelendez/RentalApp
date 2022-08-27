package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Lease;
import com.project.rentalapp.repositories.LeaseRepositroy;

@Service
public class LeaseServiceImpl implements LeaseService {
	
	@Autowired
	LeaseRepositroy leaseRepo;

	@Override
	public Lease showLease(int id) {

		
		return leaseRepo.findById(id);
	}

	@Override
	public Lease addLease(Lease lease) {

		
		return leaseRepo.saveAndFlush(lease);
	}

	@Override
	public Lease updateLease(Lease lease, int id) {
		
		Lease manage = leaseRepo.findById(id);
		
		if(manage != null) {
			
			manage.setAddress(lease.getAddress());
			manage.setDescription(lease.getDescription());
			manage.setEndDate(lease.getEndDate());
			manage.setPetDeposit(lease.getPetDeposit());
			manage.setRentalAmount(lease.getRentalAmount());
			manage.setSecurityDeposit(lease.getSecurityDeposit());
			manage.setStartDate(lease.getStartDate());
			
		}
	
		leaseRepo.saveAndFlush(manage);	

		return manage;
	}

	@Override
	public boolean destroyLease(int id) {
		
		boolean deleted = !leaseRepo.existsById(id);
		
		return deleted;
	}

	@Override
	public List<Lease> index() {

		
		return leaseRepo.findAll();
	}

}
