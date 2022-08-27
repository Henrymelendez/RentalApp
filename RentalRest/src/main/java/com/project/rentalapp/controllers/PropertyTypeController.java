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

import com.project.rentalapp.entities.PropertyType;
import com.project.rentalapp.service.PropertyTypeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class PropertyTypeController {

	@Autowired
	PropertyTypeService propTypeSvc;

	@GetMapping("propertyType")
	public List<PropertyType> getListofPropertyType(HttpServletResponse res) {

		try {
			List<PropertyType> propsTypes = propTypeSvc.index();
			if (propsTypes == null) {
				res.setStatus(404);
			}
			return propsTypes;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@PutMapping("propertyType/{id}")
	public PropertyType update(HttpServletResponse res, HttpServletRequest req, @PathVariable int id,
			Principal principal, @RequestBody PropertyType propertyType) {

		propertyType = propTypeSvc.updatePropertyType(id, propertyType);

		if (propertyType == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}

		return propertyType;
	}

	
	@PostMapping("propertyType")
	public PropertyType createNewPropertyType(HttpServletRequest req, HttpServletResponse res,
			@RequestBody PropertyType propertyType, Principal principal) {

		try {

			propertyType = propTypeSvc.addPropertyType(propertyType);

			if (propertyType == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(propertyType.getId());
				res.setHeader("Location", url.toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Property Type JSON");
			res.setStatus(400);
			propertyType = null;
		}

		return propertyType;

	}
	
	@DeleteMapping("propertyType/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		
		try {	
			if(propTypeSvc.destroyPropertyType(id)) {
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
	
	
	@GetMapping("propertyType/{id}")
	public PropertyType getOnePropertyType(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		
		
		try {
			PropertyType prop = propTypeSvc.showPropertyType(id);
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
	
	
	
	
}
