package com.RentACar.business.adapters;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.PosService;
import com.RentACar.business.abstracts.outServices.GarantiService;

@Service
@Primary
public class PosGarantiAdapter implements PosService {

	@Override
	public boolean doPayment(String cardHolder, String cardNo, String cardCvv) {
		
		GarantiService garantiService = new GarantiService();
		garantiService.makePayment(cardHolder, cardNo, cardCvv);
		
		return true;
	}

}
