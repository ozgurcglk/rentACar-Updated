package com.RentACar.business.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	@NotNull
	private String eMail;
	
	@NotNull
	@Size(min = 6, max = 50)
	private String password;
	
	@NotNull
	private String taxNumber;
	
	@NotNull
	private String companyName;

	
	
}
