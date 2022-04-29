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
@Table(name = "ordered_additional_service")
@Entity
public class OrderedAdditionalService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ordered_additional_service_id")
	private int orderedAdditionalServiceId;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private AdditionalService additionalService;
	
	@ManyToOne
	@JoinColumn(name = "renntal_id")
	private Rental rental;
	
}
