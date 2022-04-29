package com.RentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="individual_customers")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "indi_customer_id", referencedColumnName = "customer_id")
@Entity
public class IndividualCustomer extends Customer{
	
	@Column(name = "indi_customer_id", insertable = false,  updatable = false)
	private int indiCustomerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name  = "national_id_number")
	private String nationalIdNumber;

}
