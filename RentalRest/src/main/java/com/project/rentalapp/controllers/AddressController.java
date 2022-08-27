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

import com.project.rentalapp.entities.Address;
import com.project.rentalapp.service.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class AddressController {
	
	@Autowired
	AddressService addressSrv;
	
	
	@GetMapping("address/{id}")
	public Address getAnAddress(@PathVariable int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		
		try {
			Address address = addressSrv.showAddress(id);
			
			if(address == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(200);
			}
			return address;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	@PostMapping("address")
	public Address createAnAddress(HttpServletRequest req, HttpServletResponse res, @RequestBody Address address, Principal principal) {
		
		try {
			
			address = addressSrv.addAddress(address);
			
			if(address == null) {
				res.setStatus(404);
			}
			else {
				
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(address.getId());
				res.setHeader("Location", url.toString());
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.err.println("Invalid Address JSON");
			res.setStatus(400);
			address = null;
		}
		return address;
	}
	
	@PutMapping("address")
	public Address update(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal,@RequestBody Address address ) {
		
		address = addressSrv.updateAddress(address, id);
		if( address == null ) {
			res.setStatus(400);
		}
		else {
			res.setStatus(201);
		}
		return address;
	}
	
	@DeleteMapping("address/{id}")
	public void delete(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principle) {
		
		
		try {
			
			if(addressSrv.destroyAddress(id)) {
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
	
	@GetMapping("address")
	public List<Address> getAddresses(HttpServletRequest req, HttpServletResponse res){
		
		try {
			List<Address> addresses = addressSrv.index();
			
			if(addresses == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(200);
			}
			return addresses;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
