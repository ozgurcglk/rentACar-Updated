package com.RentACar.business.requests.ColorRequests;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {
	@NotNull
	@Size(min = 1, max = 40)
	private String colorName;
}
