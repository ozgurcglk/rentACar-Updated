package com.RentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column (name = "rent_date")
	private LocalDate rentDate;
	
	@Column (name = "return_date")
	private LocalDate returnDate;
	
	@Column (name = "rent_milage")
	private int rentMilage;
	
	@Column (name = "return_milage")
	private int returnMilage;
	
	@ManyToOne
	@JoinColumn (name = "car_id")
	private Car carId;
	
	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable (name = "ordered_additional_services", joinColumns = @JoinColumn(name = "rental_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
	private List<AdditionalService> orderedAdditionalService;
	
	@ManyToOne
	@JoinColumn (name = "rented_city_id")
	private City rentedCity;
	
	@ManyToOne
	@JoinColumn (name = "return_city_id")
	private City returnCity;
	
}







