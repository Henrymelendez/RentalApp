package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Maintenance;
import com.project.rentalapp.entities.Payment;
import com.project.rentalapp.entities.Tenant;
import com.project.rentalapp.repositories.TenantRepository;

@Service
public class TenantSeviceImpl implements TenantService {
	
	@Autowired
	TenantRepository tenantRepo;
	

	@Override
	public Tenant showTenent(int id) {
		// TODO Auto-generated method stub
		return tenantRepo.findById(id);
	}

	@Override
	public Tenant addTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return tenantRepo.saveAndFlush(tenant);
	}

	@Override
	public Tenant updateTenant(Tenant tenant, int id) {
		Tenant managed = tenantRepo.findById(id);
		
		if(managed != null) {
			managed.setEmail(tenant.getEmail());
			managed.setMantainance(tenant.getMantainance());
			managed.setPayments(tenant.getPayments());
			managed.setPhoneNumber(tenant.getPhoneNumber());
			managed.setProperty(managed.getProperty());
			managed.setRegistrationDate(tenant.getRegistrationDate());
			managed.setTenantName(tenant.getTenantName());
		}
		tenantRepo.saveAndFlush(managed);
		
		return managed;
	}

	@Override
	public boolean destroyTenanat(int id) {
		tenantRepo.findById(id);
		
		boolean deleted = !tenantRepo.existsById(id);
		
		return deleted;
	}

	@Override
	public List<Tenant> index() {
		// TODO Auto-generated method stub
		return tenantRepo.findAll();
	}

	@Override
	public List<Payment> getTenantPayment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Maintenance> getTenantMaintnace(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
