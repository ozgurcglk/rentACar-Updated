package com.RentACar.business.requests.CityRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {
	
	@NotNull
	@Min(1)
	private int cityId;
	
	@Size(min = 1, max = 15)
	private String cityName;
	
}
