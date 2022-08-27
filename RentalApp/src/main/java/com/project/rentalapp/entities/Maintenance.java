package com.project.rentalapp.entities;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Maintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "completed")
	private boolean completed;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "properties_id")
	private Property property;
	
	@ManyToOne
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
	
	@JsonIgnore
	@OneToMany(mappedBy = "maintananceRequest")
	private List<Contractor> contractors;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public List<Contractor> getContractors() {
		return contractors;
	}

	public void setContractors(List<Contractor> contractors) {
		this.contractors = contractors;
	}
	
	
	
	public void addContractor(Contractor cont) {
		
		if(contractors == null) {
			contractors = new ArrayList<Contractor>();
		}
		
		
		if(contractors != null && !contractors.contains(cont)) {
			cont.setMaintananceRequest(this);
			contractors.add(cont);
		}
		
	}
	
	
	public void removeContractor(Contractor cont) {
		
		cont.setMaintananceRequest(null);
		
		if(contractors != null && !contractors.contains(cont)) {
			
			contractors.remove(cont);
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
		Maintenance other = (Maintenance) obj;
		return id == other.id;
	}
	
	

	@Override
	public String toString() {
		return "Maintenance [id=" + id + ", description=" + description + ", completed=" + completed + ", property="
				+ property + ", tenant=" + tenant + ", contractors=" + contractors + "]";
	}
	
	
	
	

}
