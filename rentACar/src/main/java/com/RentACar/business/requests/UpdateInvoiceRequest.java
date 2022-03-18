package com.RentACar.business.requests;

import java.time.LocalDate;
import java.util.List;

import com.RentACar.entities.concretes.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	
	private int invoiceId;
	private String invoiceNumber;
	private LocalDate creatingDate;
	private LocalDate rentDate;
	private int totalRentDay;
	private List<Customer> customers;

}
