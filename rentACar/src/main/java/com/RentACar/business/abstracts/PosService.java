package com.RentACar.business.abstracts;

public interface PosService {
	public boolean doPayment(String cardHolder, String cardNo, String cardCvv);
}
