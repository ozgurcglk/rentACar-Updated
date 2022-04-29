package com.RentACar.business.requests.PaymentRequests;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.RentACar.business.requests.CardDetailRequests.CreateCardDetailRequest;
import com.RentACar.business.requests.RentalRequests.CreateRentalRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	
	@NotNull
	private LocalDate paymentDate;
	
	private CreateCardDetailRequest createCardDetailRequest;
	
	private List<Integer> additionalServiceIds;
	
	private CreateRentalRequest createRentalRequest;
}
