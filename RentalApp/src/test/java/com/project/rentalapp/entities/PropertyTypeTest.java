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

class PropertyTypeTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PropertyType property;

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
		property = em.find(PropertyType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		property = null;
	}

	@Test
	void test_PropertyType_mapping() {
		
		assertNotNull(property);
		assertEquals(1, property.getId());
		assertEquals("condo", property.getName());
	}
	
	
	@Test
	void test_PropertyType_property_Mapping() {
		
		assertEquals(1, property.getProperties().get(0).getId());
		assertEquals("2022-03-22", property.getProperties().get(0).getPurchaseDate());
		assertEquals(200000, property.getProperties().get(0).getPurchaseAmount());
		
	}
	
	

}
