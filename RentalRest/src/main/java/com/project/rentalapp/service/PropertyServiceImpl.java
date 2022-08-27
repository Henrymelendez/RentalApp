package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Property;
import com.project.rentalapp.repositories.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	PropertyRepository propertyRepo;

	@Override
	public Property showProperty(int id) {

		
		return propertyRepo.findById(id);
	}
	
	
	

	@Override
	public Property addProperty(Property property) {

		
		return propertyRepo.saveAndFlush(property);
	}

	@Override
	public Property updateProperty(Property property, int id) {

		Property managed = propertyRepo.findById(id);
		
		
		if(managed != null) {
			managed.setAddress(property.getAddress());
			managed.setMaintenance(property.getMaintenance());
			managed.setNotes(property.getNotes());
			managed.setPayments(property.getPayments());
			managed.setPropertyType(property.getPropertyType());
			managed.setPurchaseAmount(property.getPurchaseAmount());
			managed.setPurchaseDate(property.getPurchaseDate());
			managed.setTenants(property.getTenants());
			managed.setUser(property.getUser());
		}
		
		propertyRepo.saveAndFlush(managed);
		
		
		return managed;
	}

	@Override
	public boolean destroyProperty(int id) {

		propertyRepo.deleteById(id);
		
		boolean deleted = !propertyRepo.existsById(id);
		
		
		return deleted;
	}

	@Override
	public List<Property> index() {
		
		
		return propertyRepo.findAll();
	}





}
