package com.project.rentalapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lease")
public class Lease {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "rental_amount")
	private double rentalAmount;
	
	@Column(name = "security_deposit")
	private double securityDeposit;
	
	@Column(name = "pet_deposit")
	private double petDeposit;
	
	@OneToOne
	@JoinColumn(name ="address_id")
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRentalAmount() {
		return rentalAmount;
	}

	public void setRentalAmount(double rentalAmount) {
		this.rentalAmount = rentalAmount;
	}

	public double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public double getPetDeposit() {
		return petDeposit;
	}

	public void setPetDeposit(double petDeposit) {
		this.petDeposit = petDeposit;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lease other = (Lease) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Lease [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description
				+ ", rentalAmount=" + rentalAmount + ", securityDeposit=" + securityDeposit + ", petDeposit="
				+ petDeposit + ", address=" + address + "]";
	}

	
	
	
	
}
