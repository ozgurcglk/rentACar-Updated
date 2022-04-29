package com.RentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customer")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "customer_id",referencedColumnName = "user_id") 
public class Customer extends User{

	@Column(name="customer_id", insertable= false, updatable = false)     
	private int customerId;
	
	@OneToMany(mappedBy = "customer")
	private List<Rental> retnals;
	
	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices;
	
	@OneToMany(mappedBy = "customer")
	private List<CardDetail> cardDetails;
	
}