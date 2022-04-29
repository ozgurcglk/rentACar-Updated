package com.RentACar.business.requests.CarDamageRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
	
	@NotNull
	@Min(1)
	private int carId;
	
	@Size(min = 2, max = 20)
	private String carDamageInfo;

}