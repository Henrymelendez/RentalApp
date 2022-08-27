package com.project.rentalapp.service;

import java.util.List;

import com.project.rentalapp.entities.Address;

public interface AddressService {
	
	public Address showAddress(int id);
	public Address addAddress(Address address);
	public Address updateAddress(Address address, int id);
	public boolean destroyAddress(int id);
	public List<Address> index();

}
