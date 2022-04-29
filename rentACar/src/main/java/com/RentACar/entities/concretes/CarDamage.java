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
@Table(name="car_damages")
@Entity
public class CarDamage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_damage_id")
	private int carDamageId;
	
	@Column(name = "car_damage_info")
	private String carDamageInfo;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
}
