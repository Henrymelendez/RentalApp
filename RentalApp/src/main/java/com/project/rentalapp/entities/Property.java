package com.project.rentalapp.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "property")
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "purchase_date")
	private LocalDateTime purchaseDate;
	
	@Column(name = "purchase_amount")
	private int purchaseAmount;
	
	
	@Column(name = "notes")
	private String notes;
	
	
	@ManyToOne
	@JoinColumn(name = "property_type_id")
	private PropertyType propertyType;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//
	@JsonIgnore
	@OneToMany(mappedBy = "property")
	private List<Payment> payments;
	
	//
	
	@OneToMany(mappedBy = "property")
	private List<Tenant> tenants;
	//

	@OneToMany(mappedBy = "property")
	private List<Maintenance> maintenance;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	
	
	
	
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Maintenance> getMaintenance() {
		return maintenance;
	}


	public void setMaintenance(List<Maintenance> maintenance) {
		this.maintenance = maintenance;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}


	public int getPurchaseAmount() {
		return purchaseAmount;
	}


	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public PropertyType getPropertyType() {
		return propertyType;
	}


	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Payment> getPayments() {
		return payments;
	}


	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}


	public List<Tenant> getTenants() {
		return tenants;
	}


	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}

	
	
	public void addPayment(Payment payment){
		
		if(payments == null) {
			payments = new ArrayList<Payment>();
		}
		if(!payments.contains(payment)) {
			payment.setProperty(this);
			payments.add(payment);
			
		}
		
		
	}
	
	
	public void removePayment(Payment payment) {
		
		payment.setProperty(null);
		if(payments != null & payments.contains(payment)) {
			payments.remove(payment);
		}
	}
	
	
	public void addTenant(Tenant tenant) {
		
		if(tenants == null) {
			tenants = new ArrayList<Tenant>();
		}
		
		if(!tenants.contains(tenant)) {
			tenant.setProperty(this);
			tenants.add(tenant);
			
		}
		
	}
	
	
	public void removeTenant(Tenant tenant) {
		
		tenant.setProperty(null);
		if(tenants != null && tenants.contains(tenant)) {
			tenants.remove(tenant);
		}
	}
	
	
	
	public void addMaintnance(Maintenance m) {
		
		if(maintenance == null) {
			maintenance = new ArrayList<Maintenance>();
		}
		
		
		if(!maintenance.contains(m)) {
			m.setProperty(this);
			maintenance.add(m);
		}
		
	}
	
	public void removeMaintnance(Maintenance m) {
		
		m.setProperty(null);
		
		if(maintenance != null && maintenance.contains(m)) {
			maintenance.remove(m);
		}
	}
	
	
	
	
	
	
	
	
	
}
