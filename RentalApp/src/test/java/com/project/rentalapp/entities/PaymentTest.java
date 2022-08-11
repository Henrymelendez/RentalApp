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

class PaymentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Payment payment;

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
		payment = em.find(Payment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		payment = null;
	}


	@Test
	void test_Payment_mapping() {
		assertNotNull(payment);
		assertEquals(1, payment.getId());
		assertEquals(1500,payment.getAmount());
	}
	
	
	@Test
	void test_Payment_to_property_Mapping() {
		assertEquals(1, payment.getProperty().getId());
		assertEquals("condo", payment.getProperty().getPropertyType().getName());
		assertEquals(200000, payment.getProperty().getPurchaseAmount());
	}
	
	
	@Test
	void test_Payment_To_Tenant_Mapping() {
		assertEquals(1, payment.getTenant().getId());
		assertEquals("John", payment.getTenant().getTenantName());
	}
	

}
