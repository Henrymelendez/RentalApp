package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Property;
import com.project.rentalapp.entities.PropertyType;

public interface PropertyTypeService {
	
	public PropertyType showPropertyType(int id);
	
	public PropertyType addPropertyType(PropertyType proptype);
	
	public PropertyType updatePropertyType(int id, PropertyType propType);
	
	public boolean destroyPropertyType(int id);
	
	public List<PropertyType> index();
	
	public List<Property> getProperties(int id);
	

}
