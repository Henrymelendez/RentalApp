package com.project.rentalapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.chainsaw.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rentalapp.entities.Maintenance;
import com.project.rentalapp.entities.PropertyType;
import com.project.rentalapp.service.MaintenanceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class MaintenanceController {
	
	
	@Autowired
	MaintenanceService maintSvc;
	
	
	
	
	@GetMapping("maintenance/{id}")
	public Maintenance getOneMaintenaceRequest(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		
		Maintenance maint = maintSvc.showMaintenance(id);
		
		if(maint == null) {
			res.setStatus(404);
		}
		return maint;
	}
	
	
	@GetMapping("maintenance")
	public List<Maintenance> getListofPropertyType(HttpServletResponse res) {

		try {
			List<Maintenance> maint = maintSvc.index();
			if (maint == null) {
				res.setStatus(404);
			}
			return maint;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	@DeleteMapping("maintenance/{id}")
	public void deleteMaintenance(HttpServletResponse res, HttpServletRequest req, @PathVariable int id) {
		
		try {
			if(maintSvc.destroyMaintenance(id)) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
	
	
	@PutMapping("maintenance/{id}")
	public Maintenance update(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal, 
			@RequestBody Maintenance maint) {
		
		maint = maintSvc.updateMaintenance(maint, id);
		
		if(maint == null) {
			res.setStatus(400);
		}
		else {	
			res.setStatus(201);
		}
		return maint;	
	}
	
	
	@PostMapping("maintenance")
	public Maintenance createNewMaintenance(HttpServletRequest req, HttpServletResponse res, @RequestBody Maintenance maint,
			Principal principal) {
		
		
		try {
			
			maint = maintSvc.addMaintenance(maint);
			
			if(maint == null) {
				
				res.setStatus(404);
			}
			
			else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(maint.getId());
				res.setHeader("Location", url.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Maintenance JSON");
			res.setStatus(400);
			maint = null;
		}
		return maint;
	}
	
	

}
