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

import com.project.rentalapp.entities.Property;
import com.project.rentalapp.service.PropertyService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class PropertyController {

	@Autowired
	PropertyService propSvc;
	
	
	@GetMapping("property/{id}")
	public Property getaProperty(@PathVariable int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		
		try {
			Property prop = propSvc.showProperty(id);
			if(prop == null) {
				res.setStatus(404);
			}
			else {	
				res.setStatus(200);
			}
			return prop;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@GetMapping("property")
	public List<Property> getProperties(HttpServletResponse res, Principal principal){
		
		try {
			List<Property> properties = propSvc.index();
			
			if(properties == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(200);
			}
			return properties;
			
		} catch (Exception e) {	
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@DeleteMapping("property/{id}")
	public void delete(@PathVariable int id, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		
		try {
			if(propSvc.destroyProperty(id)) {
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
	
	@PostMapping("property")
	public Property createProperty(@RequestBody Property property, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		
		try {
			property = propSvc.addProperty(property);
			
			if(property == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(200);
			}
			return property;	
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PutMapping("property/{id}")
	public Property update(@PathVariable int id, @RequestBody Property property, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		
		try {
			property = propSvc.updateProperty(property, id);
			
			if(property == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(200);
			}
			return property;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	
}
