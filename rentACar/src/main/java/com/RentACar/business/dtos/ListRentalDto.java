package com.RentACar.business.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
	
	private int rentalId;
	private int customerId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int rentingCityId;
	private int returningCityId;
	private double totalPrice;
	private int rentMilage;
	private int returnMilage;
	private int carId;
	private List<ListAdditionalServiceDto> additionalServices;
	
}
