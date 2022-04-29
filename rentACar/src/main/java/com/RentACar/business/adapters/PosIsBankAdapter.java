package com.RentACar.business.adapters;

import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.PosService;
import com.RentACar.business.abstracts.outServices.IsBankService;

@Service
public class PosIsBankAdapter implements PosService {

	@Override
	public boolean doPayment(String cardHolder, String cardNo, String cardCvv) {
		
		IsBankService isBankService = new IsBankService();
		isBankService.makePayment(cardNo, cardHolder, cardCvv);
		
		return true;
	}

}
