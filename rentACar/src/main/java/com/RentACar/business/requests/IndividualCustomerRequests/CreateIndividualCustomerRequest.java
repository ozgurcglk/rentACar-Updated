package com.RentACar.business.requests.IndividualCustomerRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(min = 6, max = 50)
	private String password;
	
	@NotNull
	@Size(min = 1, max = 45)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 25)
	private String lastName;
	
	@NotNull
	@Size(min = 3)
	private String nationalIdNumber;

}
