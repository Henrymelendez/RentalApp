package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Maintenance;
import com.project.rentalapp.entities.Payment;
import com.project.rentalapp.entities.Tenant;

public interface TenantService {
	
	public Tenant showTenent(int id);
	
	public Tenant addTenant(Tenant tenant);
	
	public Tenant updateTenant(Tenant tenant, int id);
	
	public boolean destroyTenanat(int id);
	
	public List<Tenant> index();
	
	public List<Payment> getTenantPayment(int id);
	
	public List<Maintenance> getTenantMaintnace(int id);
	

}
