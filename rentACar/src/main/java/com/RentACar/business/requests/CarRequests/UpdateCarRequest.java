package com.RentACar.business.requests.CarRequests;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	@NotNull
	@Min(1)
	private int carId;
	
	@NotNull
	@Min(0)
	private int dailyPrice;
	
	@NotNull
	private int milage;
	
	@NotNull
	private String modelYear;
	
	@NotNull
	@Size(min = 1, max = 30)
	private String description;
	
	@NotNull
	private int brandId;
	@NotNull
	private int colorId;
	
	@Nullable
	private List<Integer> carDamageIds;
	
}
