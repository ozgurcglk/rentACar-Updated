package com.RentACar.business.requests.CarMaintenanceRequests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	@NotNull
	@Size(min=5, max=50)
	private String description;
	
	@NotNull
	private int carId;
	
}
