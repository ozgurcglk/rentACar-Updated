package com.RentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {
	
	private int carId;
	private double dailyPrice;
	private String modelYear;
	private String description;
	private int milage;

	private String brandName;
	
	private String colorName;
}
