package com.RentACar.business.abstracts.outServices;

import org.springframework.stereotype.Service;

@Service
public class IsBankService {
	public boolean makePayment(String cardNo, String cardHolder, String cardCvv) {
		return true;
	}
}
