package com.RentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCorporateCustomerDto {
	
	private int corpCustomerId;
	private String email;
	private String password;
	private String taxNumber;
	private String companyName;
	
}
