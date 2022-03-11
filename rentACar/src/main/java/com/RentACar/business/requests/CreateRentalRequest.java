package com.RentACar.business.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@NotNull
	private int customerId;
	
	@NotNull
	private LocalDate rentDate;
	
	//@NotNull
	//private LocalDate returnDate;
	
	@NotNull
	private int carId;
	
	@Nullable
	private int serviceId;
}
