package com.project.rentalapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "amount")
	private double amount;

	@Column(name = "mode_of_payment")
	private String modeOfPayment;

	@Column(name = "payment_notes")
	private String paymentNotes;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@ManyToOne
	@JoinColumn(name = "properties_id")
	private Property property;

	@ManyToOne
	@JoinColumn(name = "tenant_id")
	private Tenant tenant;

	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getPaymentNotes() {
		return paymentNotes;
	}

	public void setPaymentNotes(String paymentNotes) {
		this.paymentNotes = paymentNotes;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
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
		Payment other = (Payment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", modeOfPayment=" + modeOfPayment + ", paymentNotes="
				+ paymentNotes + ", paymentDate=" + paymentDate + ", property=" + property + ", tenant=" + tenant + "]";
	}
	
	
	

}
