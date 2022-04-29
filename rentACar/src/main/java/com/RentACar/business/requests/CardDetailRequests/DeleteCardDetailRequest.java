package com.RentACar.business.requests.CardDetailRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCardDetailRequest {
	@NotNull
	private int cardDetailId;
	
}