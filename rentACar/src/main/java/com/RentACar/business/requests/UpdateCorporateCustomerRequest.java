package com.RentACar.business.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	@NotNull
	@Min(1)
	private int id;
	
	@NotNull
	private String eMail;
	
	@NotNull
	private String taxNumber;
	
	@NotNull
	private String companyName;
}
