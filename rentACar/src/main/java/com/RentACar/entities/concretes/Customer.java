package com.RentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@PrimaryKeyJoinColumn(name = "customer_id",referencedColumnName = "user_id") 
public class Customer extends User{

	@Column(name="customer_id", insertable= false, updatable = false)     
	private int customerId;
	
	@OneToMany
	@JoinColumn (name = "rental_id")
	// mappedBy olması gerek
	// cascade olabilir
	private List<Rental> rentals;
	
	@OneToMany(mappedBy = "invoiceId")
	private List<Invoice> invoice;
	
	@OneToMany(mappedBy = "customer")
	private List<CardDetail> cardDetails;
	
}