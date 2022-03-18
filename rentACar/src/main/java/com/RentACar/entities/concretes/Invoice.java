package com.RentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="invoices")
@Entity
public class Invoice {
	
	@Column(name = "invoice_id")
	private int invoiceId;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "creating_date")
	private LocalDate creatingDate;
	
	@Column(name = "rent_date")
	private LocalDate rentDate;
	
	@Transient
	private int totalRentDay;
	
	@Column(name = "total")
	private double total;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private List<Customer> customers;
	
}
