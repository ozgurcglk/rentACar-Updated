package com.RentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {
	private int paymentId;
	private LocalDate paymentDate;
	private double totalPayment;
	private int cardDetailId;
	private int customerId;
	private int rentalId;
	private int invoiceId;
}
