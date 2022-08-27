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

import com.project.rentalapp.entities.Lease;
import com.project.rentalapp.service.LeaseService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class LeaseController {

	@Autowired
	LeaseService leaseSvc;

	@GetMapping("lease/{id}")
	public Lease getaLease(@PathVariable int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {

		try {
			Lease lease = leaseSvc.showLease(id);

			if (lease == null) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
			}
			return lease;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@GetMapping("lease")
	public List<Lease> getLeases(HttpServletRequest req, HttpServletResponse res, Principal principal) {

		try {
			List<Lease> leases = leaseSvc.index();

			if (leases == null) {
				res.setStatus(204);
			}
			return leases;

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}
	
	@DeleteMapping("lease/{id}")
	public void deleteLease(HttpServletRequest req, HttpServletResponse res,@PathVariable int id, Principal principal) {
		
		
		try {
			if(leaseSvc.destroyLease(id)) {
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
	
	@PutMapping("lease/{id}")
	public Lease update(@PathVariable int id,@RequestBody Lease lease,HttpServletRequest req, HttpServletResponse res, Principal principal) {
		
		try {
			lease = leaseSvc.updateLease(lease, id);
			
			if(lease == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(200);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return lease;
	}
	
	@PostMapping("lease")
	public Lease createLease(HttpServletRequest req, HttpServletResponse res, @RequestBody Lease lease,
			Principal principal) {
		
		try {
			lease = leaseSvc.addLease(lease);
			
			if(lease == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(lease.getId());
				res.setHeader("Location", url.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Lease JSON");
			res.setStatus(400);
			lease = null;
		}
		return lease;
	}
	
}
