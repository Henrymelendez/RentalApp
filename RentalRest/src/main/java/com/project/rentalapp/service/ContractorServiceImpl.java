package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Contractor;
import com.project.rentalapp.repositories.ContractorRepository;

@Service
public class ContractorServiceImpl implements ContractorService {
	
	@Autowired
	ContractorRepository contractorRepo;

	@Override
	public Contractor showContractor(int id) {
		// TODO Auto-generated method stub
		return contractorRepo.findById(id);
	}

	@Override
	public Contractor addContractor(Contractor contractor) {
		// TODO Auto-generated method stub
		return contractorRepo.saveAndFlush(contractor);
	}

	@Override
	public Contractor updateContractor(Contractor contractor, int id) {
		
		Contractor managed = contractorRepo.findById(id);
		
		if(managed != null) {
			
			managed.setContactAddress(managed.getContactAddress());
			managed.setContractorName(contractor.getContractorName());
			managed.setMaintananceRequest(contractor.getMaintananceRequest());
	
			
		}
		
		contractorRepo.saveAndFlush(managed);
		
		return managed;
	}

	@Override
	public boolean destroyContractor(int id) {
		
		contractorRepo.deleteById(id);
		
		boolean deleted = !contractorRepo.existsById(id);
		
		return deleted;
	}

	@Override
	public List<Contractor> index() {
		// TODO Auto-generated method stub
		return contractorRepo.findAll();
	}

	

}
