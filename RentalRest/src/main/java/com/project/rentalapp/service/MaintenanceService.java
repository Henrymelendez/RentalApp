package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Maintenance;

public interface MaintenanceService {
	
	public Maintenance showMaintenance(int id);
	public Maintenance addMaintenance(Maintenance maintenance);
	public Maintenance updateMaintenance(Maintenance maintenance, int id);
	public boolean destroyMaintenance(int id);
	public List<Maintenance> index();


}
