package com.RentACar.business.dtos;

import java.time.LocalDate;
import java.util.List;

import com.RentACar.entities.concretes.Customer;

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
	private int totalRentDay;
	private double total;
	private List<Customer> customers;
}
