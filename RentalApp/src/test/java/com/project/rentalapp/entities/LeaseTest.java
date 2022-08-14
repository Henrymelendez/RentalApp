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

class LeaseTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Lease lease;

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
		lease = em.find(Lease.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		lease = null;
	}

	@Test
	void test_lease_Entity_Mapping() {
		
		assertEquals(1, lease.getId());
		assertEquals(2022, lease.getEndDate().getYear());
		assertEquals(2022, lease.getStartDate().getYear());
	}
	
	@Test
	void test_Lease_To_Address_Mapping() {
		assertEquals(1, lease.getAddress().getId());
		assertEquals("revere", lease.getAddress().getCity());
	}

	
}
