package com.RentACar.business.requests.AdditionalServiceRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	
	@NotNull
	@Min(1)
	private int serviceId;
	
	@NotNull
	@Size(min = 2, max = 40)
	private String serviceName;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String serviceDescription;
	
	@NotNull
	@PositiveOrZero
	@Min(0)
	private double servicePrice;
	
}
