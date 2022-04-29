package com.RentACar.business.requests.OrderedAdditionalServiceRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalServiceRequest {
	
	@NotNull
	@Min(1)
	private int serviceId;
	
	@NotNull
	@Min(1)
	private int rentalId;
}
