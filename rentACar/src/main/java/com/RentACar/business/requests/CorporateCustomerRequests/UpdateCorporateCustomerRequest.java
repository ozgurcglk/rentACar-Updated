package com.RentACar.business.requests.CorporateCustomerRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	@NotNull
	@Min(1)
	private int corpCustomerId;
	
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
