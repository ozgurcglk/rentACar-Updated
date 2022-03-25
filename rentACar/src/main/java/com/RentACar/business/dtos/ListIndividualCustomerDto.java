package com.RentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListIndividualCustomerDto {
	
	private int customerId;
	private String eMail;
	private String firstName;
	private String lastName;

}
