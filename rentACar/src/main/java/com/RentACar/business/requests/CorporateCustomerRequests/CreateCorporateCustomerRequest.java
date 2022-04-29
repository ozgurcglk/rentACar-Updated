package com.RentACar.business.requests.CorporateCustomerRequests;

import javax.validation.constraints.Email;
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
	@Email
	private String email;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String password;
	
	@NotNull
	private String taxNumber;
	
	@NotNull
	private String companyName;

	
	
}
