package com.RentACar.entities.concretes;

import java.time.LocalDate;

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
@Table(name="rentals")
@Entity
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private int rentalId;
	
	@Column (name = "customer_id")
	private int customerId;
	
	@Column (name = "rent_date")
	private LocalDate rentDate;
	
	@Column (name = "return_date")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn (name = "car_id")
	private Car carId;
	
	@ManyToOne
	@JoinColumn (name = "service_id")
	private AdditionalService additionalService;
}
