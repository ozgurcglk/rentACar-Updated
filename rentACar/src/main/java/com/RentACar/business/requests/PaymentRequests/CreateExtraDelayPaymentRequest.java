package com.RentACar.business.requests.PaymentRequests;

import com.RentACar.business.requests.CardDetailRequests.CreateCardDetailRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExtraDelayPaymentRequest {
	private int rentalId;
	private CreateCardDetailRequest createCardDetailRequest;
}
