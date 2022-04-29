package com.RentACar.business.abstracts.outServices;

import org.springframework.stereotype.Service;

@Service
public class GarantiService {
	public boolean makePayment(String cardHolder, String cardNo, String cardCvv) {
		return true;
	}
}
