package com.RentACar.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@Column(name = "total_payment")
	private double totalPayment;
	
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "rental_id")
	private int rentalId;
	
	@OneToOne
	@JoinColumn(name = "invoice_id")
	private Invoice Invoice;
	
}
