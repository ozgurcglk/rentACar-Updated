package com.RentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="additional_services")
@Entity
public class AdditionalService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private int serviceId;
	
	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "service_description")
	private String serviceDescription;
	
	@Column(name = "service_price")
	private double servicePrice;
	
	@OneToMany(mappedBy = "additionalService")
	private List<OrderedAdditionalService> orderedAdditionalServices;
	
}
