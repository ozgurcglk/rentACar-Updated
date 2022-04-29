package com.RentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="card_details")
@Entity
public class CardDetail {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_detail_id")
	private int cardDetailId;
	
	@Column(name = "card_holder")
	private String cardHolder;
	
	@Column(name = "card_no")
	private String cardNo;
	
	@Column(name = "card_cvv")
	private String cardCvv;
	
	@Column(name = "card_expiration_month")
	private int cardExpirationMonth;
	
	@Column(name = "card_expiration_year")
	private int cardExpirationYear;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}




