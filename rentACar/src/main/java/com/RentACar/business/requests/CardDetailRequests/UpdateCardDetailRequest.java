package com.RentACar.business.requests.CardDetailRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardDetailRequest {
	@NotNull
	private int cardDetailId;
	
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
	
	@NotNull
	private int customerId;
	
}