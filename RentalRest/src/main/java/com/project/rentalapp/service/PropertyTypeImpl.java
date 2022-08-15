package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Property;
import com.project.rentalapp.entities.PropertyType;
import com.project.rentalapp.repositories.PropertyTypeRepository;

@Service
public class PropertyTypeImpl implements PropertyTypeService {
	
	@Autowired
	PropertyTypeRepository propTypeRepo;

	@Override
	public PropertyType showPropertyType(int id) {
		// TODO Auto-generated method stub
		return propTypeRepo.findById(id);
	}

	@Override
	public PropertyType addPropertyType(PropertyType proptype) {
		
		return propTypeRepo.saveAndFlush(proptype);
	}

	@Override
	public PropertyType updatePropertyType(int id, PropertyType propType) {
		
		PropertyType managed = propTypeRepo.findById(id);
		
		if(managed != null) {
			managed.setDescription(propType.getDescription());
			managed.setName(propType.getName());
			managed.setProperties(propType.getProperties());
		}
		
		propTypeRepo.saveAndFlush(managed);
		
		return managed;
	}

	@Override
	public boolean destroyPropertyType(int id) {
		propTypeRepo.deleteById(id);
		boolean deleted = !propTypeRepo.existsById(id);
			
		return deleted;
	}

	@Override
	public List<PropertyType> index() {
		
		
		
		return propTypeRepo.findAll();
	}
	
	// FIXME ALL PROPERTIES 

	@Override
	public List<Property> getProperties(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
