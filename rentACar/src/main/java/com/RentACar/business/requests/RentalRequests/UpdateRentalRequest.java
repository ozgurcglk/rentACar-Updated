package com.RentACar.business.requests.RentalRequests;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	@NotNull
	private int rentalId;
	
	@NotNull
	private LocalDate rentDate;
	
	@NotNull
	private LocalDate returnDate;
	
	@NotNull
	private int returnMilage;
	
	@NotNull
	private int rentingCityId;
	
	@NotNull
	private int returningCityId;
	
	@NotNull
	private double totalPrice;
	
	@NotNull
	private int carId;
	
	@NotNull
	private int customerId;
	
	@NotNull
	private List<Integer> orderedAdditionalServiceIds;
	
}
