package com.project.rentalapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name = "role")
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Property> properties;
	
	
	
	
	public User() {
		super();
		
	}
	


	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addProperty(Property prop) {
		
		if(properties == null) {
			properties = new ArrayList<Property>();
		}
		
		if(!properties .contains(prop)) {
			prop.setUser(this);
			properties.add(prop);
		}
		
	}
	
	
	public void removeProperty(Property prop) {
		
		prop.setUser(null);
		
		if(properties != null & properties.contains(prop)) {
			properties.remove(prop);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", properties=" + properties + "]";
	}
	
	
	
	

}
