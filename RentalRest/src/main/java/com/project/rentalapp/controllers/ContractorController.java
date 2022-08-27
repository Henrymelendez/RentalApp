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

import com.project.rentalapp.entities.Contractor;
import com.project.rentalapp.service.ContractorService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ContractorController {
	
	@Autowired
	ContractorService contractorSvc;
	
	
	@GetMapping("contractor/{id}")
	public Contractor getOneContractor(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		
		try {
			
			Contractor contractor = contractorSvc.showContractor(id);
			
			if(contractor == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(200);
			}
			return contractor;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}	
	}
	
	@DeleteMapping("contractor/{id}")
	public void deleteContractor(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		
		
		try {
			if(contractorSvc.destroyContractor(id)) {
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
	
	@PostMapping("contractor")
	public Contractor createNewContractor(HttpServletRequest req, HttpServletResponse res, @RequestBody Contractor contractor, Principal principal ) {
		
		try {
			contractor = contractorSvc.addContractor(contractor);
			
			if(contractor == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(contractor.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			System.err.println("Invalid Contractor JSON");
			res.setStatus(400);
			contractor = null;
		}
		return contractor;
	}
	
	
	@PutMapping("contractor/{id}")
	public Contractor update(HttpServletResponse res, HttpServletRequest req, @PathVariable int id,
			Principal principal, @RequestBody Contractor contractor) {
		
		contractor = contractorSvc.updateContractor(contractor, id);
		
		if(contractor == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(201);
		}
		return contractor;
		
	}
	
	@GetMapping("contractor")
	public List<Contractor> getContractors(HttpServletResponse res){
		
		try {
			List<Contractor> contrator = contractorSvc.index();
			
			if(contrator == null) {
				res.setStatus(404);
			}
			return contrator;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
