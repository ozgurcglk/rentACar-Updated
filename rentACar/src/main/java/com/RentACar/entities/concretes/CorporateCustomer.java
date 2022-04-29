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
@Table(name="corporate_customer")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "corp_customer_id", referencedColumnName = "customer_id")
@Entity
public class CorporateCustomer extends Customer {
	
	@Column(name = "corp_customer_id", insertable = false,  updatable = false)
	private int corpCustomerId;
	
	@Column(name = "tax_number")
	private String taxNumber;
	
	@Column(name = "company_name")
	private String companyName;
	
}
