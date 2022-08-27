package com.project.rentalapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.project.rentalapp.entities.Tenant;
import com.project.rentalapp.service.TenantService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class TenantController {
	
	@Autowired
	TenantService tenantSvc;
	
	
	@GetMapping("tenant/{id}")
	public Tenant getTenant(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		
		Tenant tenant = tenantSvc.showTenent(id);
		
		if(tenant == null) {
			res.setStatus(404);
		}
		
		return tenant;	
	}
	
	@DeleteMapping("tenant/{id}")
	public void deleteTenant(HttpServletRequest req, HttpServletResponse res, @PathVariable int id ) {
		
		try {
			if(tenantSvc.destroyTenanat(id)) {
				res.setStatus(201);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);	
		}
	}
	
	@PutMapping("tenant/{id}")
	public Tenant update(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal, @RequestBody Tenant tenant) {
		tenant = tenantSvc.updateTenant(tenant, id);
		
		if(tenant == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(201);
		}
		return tenant;
	}
	
	@GetMapping("tenant")
	public List<Tenant> getTenants(HttpServletResponse res, HttpServletRequest req, Principal principal){
		
		try {
			List<Tenant> tenant = tenantSvc.index();
			
			if(tenant == null) {
				res.setStatus(404);
			}
			
			return tenant;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	
	@PostMapping("tenant")
	public Tenant createNewTenant(HttpServletRequest req, HttpServletResponse res, @RequestBody Tenant tenant, Principal principal) {
		
		try {
			tenant = tenantSvc.addTenant(tenant);
			
			if(tenant == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(tenant.getId());
				res.setHeader("Location", url.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Tenant Json");
			res.setStatus(400);
			tenant = null;
	}
		return tenant;
	}

	

}
