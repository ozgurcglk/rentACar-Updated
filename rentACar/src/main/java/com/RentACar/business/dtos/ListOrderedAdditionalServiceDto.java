package com.RentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderedAdditionalServiceDto {
	
	private int orderedAdditionalServiceId;
	private int serviceId;
	private int rentalId;
	
}
