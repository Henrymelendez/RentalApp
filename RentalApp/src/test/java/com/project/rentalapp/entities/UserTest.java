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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
		
	}

	@Test
	void test_User_Entity_Mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUserName());
		assertEquals(1, user.getId());
	}
	
	
	@Test
	void test_User_Property_Mapping() {
		
		assertNotNull(user.getProperties());
		assertTrue(user.getProperties().size() > 0);
		assertEquals("condo", user.getProperties().get(0).getPropertyType().getName());
		
	}

}
