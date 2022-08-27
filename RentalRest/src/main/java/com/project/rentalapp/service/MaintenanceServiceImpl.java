package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Maintenance;
import com.project.rentalapp.repositories.MaintenanceRepository;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
	
	@Autowired
	MaintenanceRepository maintRepo;

	@Override
	public Maintenance showMaintenance(int id) {
		// TODO Auto-generated method stub
		return maintRepo.findById(id);
	}

	@Override
	public Maintenance addMaintenance(Maintenance maintenance) {
		// TODO Auto-generated method stub
		return maintRepo.saveAndFlush(maintenance);
	}

	@Override
	public Maintenance updateMaintenance(Maintenance maintenance, int id) {
		
		Maintenance managed = maintRepo.findById(id);
		
		if(managed != null) {
			
			managed.setCompleted(maintenance.isCompleted());
			managed.setContractors(maintenance.getContractors());
			managed.setDescription(maintenance.getDescription());
			managed.setProperty(maintenance.getProperty());
			managed.setTenant(maintenance.getTenant());
		}
		maintRepo.saveAndFlush(maintenance);
		
		
		return maintenance;
	}

	@Override
	public boolean destroyMaintenance(int id) {
		maintRepo.deleteById(id);
		
		boolean deleted = !maintRepo.existsById(id);
		
		return deleted;
	}

	@Override
	public List<Maintenance> index() {
		// TODO Auto-generated method stub
		return maintRepo.findAll();
	}

	



}
