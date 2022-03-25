package com.RentACar.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardDetailRequest {
	
	private String cardHolder;
	
	private String cardNo;
	
	private String cardCvv;
	
	private String cardExpirationDate;
	
	private int customerId;
	
}