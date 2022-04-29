package com.RentACar.business.requests.InvoiceRequests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

	@NotNull
	private String invoiceNumber;
	
	@NotNull
	@Min(1)
	private int customerId;
	
	@NotNull
	@Min(1)
	private int rentalId;

}
