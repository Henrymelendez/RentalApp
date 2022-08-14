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

class PropertyTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Property property;

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
		property = em.find(Property.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		property = null;
	}

	@Test
	void test_Property_Entity_Mapping() {
		
		assertEquals(1, property.getId());
		assertEquals(200000, property.getPurchaseAmount());
		
	}
	
	@Test
	void test_Property_To_address() {
		
		assertEquals(1, property.getAddress().getId());
		assertEquals("revere", property.getAddress().getCity());
		assertEquals("MA", property.getAddress().getState());
	}
	
	@Test
	void test_Property_To_Maintnance() {
		
		assertEquals(1, property.getMaintenance().get(0).getId());
		assertEquals(true, property.getMaintenance().get(0).isCompleted());
	}
	
	@Test
	void test_Property_To_propertyType_Mapping() {
		
		assertEquals(1, property.getPropertyType().getId());
		assertEquals("condo", property.getPropertyType().getName());
		
	}
	
	@Test
	void test_Property_To_User() {
		
		assertEquals(1, property.getUser().getId());
		assertEquals("admin", property.getUser().getUserName());
	}
	
	@Test
	void test_Property_To_Payment() {
		
		assertEquals(1500, property.getPayments().get(0).getAmount());
		assertEquals("cash", property.getPayments().get(0).getModeOfPayment());
		
	}
	
	@Test
	void test_Property_To_Tenant() {
		
		assertEquals(1, property.getId());
		assertEquals(2022, property.getPurchaseDate().getYear());
		assertEquals("7815584394", property.getTenants().get(0).getPhoneNumber());
	}
	
	

}
