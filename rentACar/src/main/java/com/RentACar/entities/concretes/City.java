package com.RentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cities")
@Entity
public class City {
	
	@Id
	@Column(name = "city_id")
	private int cityId;
	
	@Column(name = "city_name")
	private String cityName;
	
	@OneToMany (mappedBy = "rentingCity")
	private List<Rental> rentalRentingCity;
	
	@OneToMany (mappedBy = "returningCity")
	private List<Rental> rentalReturningCity;
}
