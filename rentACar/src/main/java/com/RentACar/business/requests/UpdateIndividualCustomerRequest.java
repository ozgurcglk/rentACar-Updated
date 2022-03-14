package com.RentACar.business.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	
	@NotNull
	@Min(1)
	private int id;
	
	@NotNull
	private String eMail;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
}
