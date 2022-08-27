package com.project.rentalapp.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
@Table(name = "tenant")
public class Tenant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "registration_date")
	private LocalDateTime registrationDate;
	
	
	@Column(name = "tenant_name")
	private String tenantName;
	
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	
	
	@Column(name = "email")
	private String email;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "properties_id")
	private Property property;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tenant")
	private List<Payment> payments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tenant")
	private List<Maintenance> mantainance;
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Maintenance> getMantainance() {
		return mantainance;
	}

	public void setMantainance(List<Maintenance> mantainance) {
		this.mantainance = mantainance;
	}
	
	
	public void addPayments(Payment pay) {
		
		if(payments == null) {
			payments = new ArrayList<Payment>();
		}
		
		if(!payments.contains(pay)) {
			pay.setTenant(this);
			payments.add(pay);
		}
		
	}
	
	public void removePayment(Payment pay) {
		
		pay.setTenant(null);
		
		if(payments != null && payments.contains(pay)) {
			payments.remove(pay);
			
		}
		
	}
	
	public void addMaintnance(Maintenance maint) {	
		
		if(mantainance == null) {
			mantainance = new ArrayList<Maintenance>();
		}
		
		if(!mantainance.contains(maint)) {
			maint.setTenant(this);
			mantainance.add(maint);
		}	
	}
	
	
	public void removeMaintenace(Maintenance maint) {
		
		maint.setTenant(null);
		
		if(mantainance !=null && mantainance.contains(maint)) {
			mantainance.remove(maint);
		}
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
		Tenant other = (Tenant) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", registrationDate=" + registrationDate + ", tenantName=" + tenantName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", property=" + property + ", payments="
				+ payments + ", mantainance=" + mantainance + "]";
	}
	
	
	

}
