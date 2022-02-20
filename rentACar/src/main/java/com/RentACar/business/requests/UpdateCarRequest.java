package com.RentACar.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	private int carId;
	private int dailyPrice;
	private String modelYear;
	private String description;
	private int brandId;
	private int colorId;
	
}
