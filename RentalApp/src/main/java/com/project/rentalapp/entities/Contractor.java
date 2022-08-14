package com.project.rentalapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contractor {

	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id;
	
	@Column(name = "contractor_name")
	private String contractorName;
	
	@Column(name = "contact_address")
	private String contactAddress;
	
	
	@ManyToOne
	@JoinColumn(name = "maintenance_id")
	private Maintenance maintananceRequest;
	
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContractorName() {
		return contractorName;
	}


	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}


	public String getContactAddress() {
		return contactAddress;
	}


	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}


	public Maintenance getMaintananceRequest() {
		return maintananceRequest;
	}


	public void setMaintananceRequest(Maintenance maintananceRequest) {
		this.maintananceRequest = maintananceRequest;
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
		Contractor other = (Contractor) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "Contractor [id=" + id + ", contractorName=" + contractorName + ", contactAddress=" + contactAddress
				+ ", maintananceRequest=" + maintananceRequest + "]";
	}
	
	
	
	
	
	
	
	
}
