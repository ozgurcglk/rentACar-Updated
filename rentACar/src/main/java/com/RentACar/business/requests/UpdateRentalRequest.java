package com.RentACar.business.requests;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.RentACar.business.dtos.ListAdditionalServiceIdDto;

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
	private int id;
	
	@NotNull
	private LocalDate rentDate;
	
	@NotNull
	private LocalDate returnDate;
	
	@NotNull
	private int carId;
	
	@Nullable
	private List<ListAdditionalServiceIdDto> serviceIds;
}
