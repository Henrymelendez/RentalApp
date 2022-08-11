package com.project.rentalapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Maintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "completed")
	private boolean completed;
	
	@ManyToOne
	@JoinColumn(name = "properties_id")
	private Property property;
	
	@ManyToOne
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;
	
	

}
