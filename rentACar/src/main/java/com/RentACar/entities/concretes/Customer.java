package com.RentACar.entities.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
public class Customer extends User{

	@OneToMany
	@JoinColumn (name = "rental_id")
	private List<Rental> rentals;
	
	
}
