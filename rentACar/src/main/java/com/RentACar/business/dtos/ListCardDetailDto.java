package com.RentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCardDetailDto {
	
	private int cardDetailId;
	
	private String cardHolder;
	
	private String cardNo;
	
	private String cardCvv;
	
	private int cardExpirationMonth;
	
	private int cardExpirationYear;
	
	private ListCustomerDto listCustomerDto;
	
}
