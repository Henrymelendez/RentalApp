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

class MaintenanceTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Maintenance maintenance;

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
		maintenance = em.find(Maintenance.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		maintenance = null;
	}

	@Test
	void test_Maintenace_Entity_Mapping() {
		
		assertEquals(1, maintenance.getId());
		assertEquals("fix water heater", maintenance.getDescription());
		
	}
	
	
	@Test
	void test_Maintnance_To_Contractor() {
		
		assertEquals(1, maintenance.getContractors().get(0).getId());
		assertEquals("john heater service", maintenance.getContractors().get(0).getContractorName());
		
	}
	
	@Test
	void test_Maintenance_To_Property() {
		
		assertEquals(1, maintenance.getProperty().getId());
		assertEquals(2022, maintenance.getProperty().getPurchaseDate().getYear());
	}

}
