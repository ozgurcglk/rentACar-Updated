package com.RentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column (name = "rent_date")
	private LocalDate rentDate;
	
	@Column (name = "return_date")
	private LocalDate returnDate;
	
	@Column (name = "rent_milage")
	private int rentMilage;
	
	@Column (name = "return_milage")
	private int returnMilage;
	
	@Column (name = "total_price")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "renting_city_id")
	private City rentingCity;
	
	@ManyToOne
	@JoinColumn(name = "returning_city_id")
	private City returningCity;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@OneToMany(mappedBy = "rental")
	private List<OrderedAdditionalService> orderedAdditionalServices;
	
	@OneToMany(mappedBy = "rental")
	private List<Invoice> invoices;
}







