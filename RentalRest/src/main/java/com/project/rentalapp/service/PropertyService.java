package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Property;

public interface PropertyService {
	
	public Property showProperty(int id);
	public Property addProperty(Property property);
	public Property updateProperty(Property property, int id);
	public boolean destroyProperty(int id);
	public List<Property> index();


}
