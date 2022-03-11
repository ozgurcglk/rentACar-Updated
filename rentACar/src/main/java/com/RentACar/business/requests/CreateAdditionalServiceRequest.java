package com.RentACar.business.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {
	
	@NotNull
	@Size(min = 2, max = 25)
	private String serviceName;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String serviceDescription;
	
	@NotNull
	@Min(0)
	private int servicePrice;
}
