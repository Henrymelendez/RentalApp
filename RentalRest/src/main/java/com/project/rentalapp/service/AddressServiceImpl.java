package com.project.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentalapp.entities.Address;
import com.project.rentalapp.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepo;

	@Override
	public Address showAddress(int id) {
		
		
		return addressRepo.findById(id);
	}

	@Override
	public Address addAddress(Address address) {

		return addressRepo.saveAndFlush(address);
	}

	@Override
	public Address updateAddress(Address address, int id) {
		
		Address managed = addressRepo.findById(id);
		
		
		if(managed != null) {
			managed.setCity(address.getCity());
			managed.setState(address.getState());
			managed.setStreet(address.getStreet());
			managed.setStreet2(address.getStreet2());
			managed.setZip(address.getZip());
		}
		
		addressRepo.saveAndFlush(managed);

		
		return managed;
	}

	@Override
	public boolean destroyAddress(int id) {

		addressRepo.deleteById(id);
		
		boolean deleted = !addressRepo.existsById(id);
		
		
		return deleted;
	}

	@Override
	public List<Address> index() {
		
		
		return addressRepo.findAll();
	}

}
