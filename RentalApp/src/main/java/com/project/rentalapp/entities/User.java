package com.project.rentalapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private int id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;

}
