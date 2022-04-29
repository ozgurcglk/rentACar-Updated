package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListPaymentDto;
import com.RentACar.business.requests.PaymentRequests.CreateExtraDelayPaymentRequest;
import com.RentACar.business.requests.PaymentRequests.CreatePaymentRequest;
import com.RentACar.business.requests.PaymentRequests.DeletePaymentRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface PaymentService {
	
	Result add(boolean rememberCard, CreatePaymentRequest createPaymentRequest) throws BusinessException;
	Result addExtraDelayPayment(CreateExtraDelayPaymentRequest createExtraDelayPaymentRequest) throws BusinessException;
	
	DataResult<List<ListPaymentDto>> getAll();
	
	Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException;
}
