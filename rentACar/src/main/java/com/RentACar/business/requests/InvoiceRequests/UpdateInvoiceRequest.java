package com.RentACar.business.requests.InvoiceRequests;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	
	@NotNull
	@Min(1)
	private int invoiceId;
	
	@NotNull
	private String invoiceNumber;
	
	@NotNull
	private LocalDate creatingDate;
	
	@NotNull
	@Min(1)
	private int rentalId;
	
	@NotNull
	@Min(1)
	private int customerId;


}
