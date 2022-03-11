package com.RentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdditionalServiceDto {
	
	private int serviceId;
	private String serviceName;
	private String serviceDescription;
	private int servicePrice;
	
}
