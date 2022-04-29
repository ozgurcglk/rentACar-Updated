package com.RentACar.business.requests.CardDetailRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardDetailRequest {
	@NotNull
	private String cardHolder;
	
	@NotNull
	private String cardNo;
	
	@NotNull
	private String cardCvv;
	
	@NotNull
	private int cardExpirationMonth;
	
	@NotNull
	private int cardExpirationYear;
	
	@Min(1)
	private int customerId;
	
}