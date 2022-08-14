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

class TenantTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Tenant tenant;

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
		tenant = em.find(Tenant.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tenant = null;
	}



	@Test
	void test_Tenant_Mapping() {
		assertEquals(1, tenant.getId());
		assertEquals("7815584394", tenant.getPhoneNumber());
	}
	
	
	@Test
	void test_Tenant_To_Payment() {
		
		assertEquals(1, tenant.getPayments().get(0).getId());
		assertEquals("cash", tenant.getPayments().get(0).getModeOfPayment());
		assertEquals(1500, tenant.getPayments().get(0).getAmount());
		
	}
	
	
	@Test
	void test_Tenant_To_Property() {
		assertEquals(1, tenant.getProperty().getId());
		assertEquals(22, tenant.getProperty().getPurchaseDate().getDayOfMonth());
		assertEquals(200000, tenant.getProperty().getPurchaseAmount());
	}
	
	@Test
	void test_Tenant_To_Maintenance() {
		
		assertEquals(1, tenant.getMantainance().get(0).getId());
		assertEquals("fix water heater", tenant.getMantainance().get(0).getDescription());
		
	}

}
