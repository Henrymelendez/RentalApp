package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Contractor;

public interface ContractorService {
	
	public Contractor showContractor(int id);
	public Contractor addContractor(Contractor contractor);
	public Contractor updateContractor(Contractor contractor, int id);
	public boolean destroyContractor(int id);
	public List<Contractor> index();

	

}
