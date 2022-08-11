package com.project.rentalapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "property")
	private List<Payment> payments;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "property")
	private List<Tenant> tenants;
	
	@JsonIgnore
	@OneToMany(mappedBy = "property")
	private List<Maintenance> maintenance;
	
	
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


	
	
	
	
	
}
