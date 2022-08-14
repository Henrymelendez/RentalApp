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

class ContractorTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Contractor contractor;

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
		contractor = em.find(Contractor.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		contractor = null;
	}

	
	
	@Test
	void test_Contractor_Entity_Mapping() {
		assertNotNull(contractor);
		assertEquals(1, contractor.getId());
		
	}
	
	
	@Test
	void test_Contractor_To_Maintenance_Mapping() {
		
		assertEquals(1, contractor.getMaintananceRequest().getId());
		assertEquals("fix water heater", contractor.getMaintananceRequest().getDescription());
		
	}
	
	

}
