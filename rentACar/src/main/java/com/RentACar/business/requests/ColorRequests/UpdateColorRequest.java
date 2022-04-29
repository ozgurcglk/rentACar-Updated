package com.RentACar.business.requests.ColorRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	@NotNull
	@Min(1)
	private int colorId;
	
	@NotNull
	@Size(min = 1, max = 4)
	private String colorName;

}
