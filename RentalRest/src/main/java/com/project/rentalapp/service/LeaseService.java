package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Lease;

public interface LeaseService {
	
	public Lease showLease(int id);
	public Lease addLease(Lease lease);
	public Lease updateLease(Lease lease, int id);
	public boolean destroyLease(int id);
	public List<Lease> index();
	

}
