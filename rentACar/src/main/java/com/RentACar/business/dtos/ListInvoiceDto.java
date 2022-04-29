package com.RentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInvoiceDto {
	
	private int invoiceId;
	private String invoiceNumber;
	private LocalDate creatingDate;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int totalRentDay;
	private double total;
	
	private int customerId;
	private int rentalId;
}
