package com.project.rentalapp.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address address;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("RentalApp");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}


	@Test
	void test_Adress_Entity_Mapping() {
		
		assertEquals("revere", address.getCity());
		assertEquals("1 Agawam Street", address.getStreet());	
		
	}
	
	@Test
	void test_Address_To_Property_Mapping() {
		
		assertEquals(1, address.getProperty().getId());
		assertEquals(2022, address.getProperty().getPurchaseDate().getYear());
		
	}
	
	@Test
	void test_Address_To_Lease_Mapping() {
		
		assertEquals(1, address.getLease().getId());
		assertEquals(2022, address.getLease().getEndDate().getYear());
	}
	
	
	

}
