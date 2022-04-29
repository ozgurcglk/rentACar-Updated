package com.RentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.PaymentService;
import com.RentACar.business.dtos.ListPaymentDto;
import com.RentACar.business.requests.PaymentRequests.CreatePaymentRequest;
import com.RentACar.business.requests.PaymentRequests.DeletePaymentRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
	
	private PaymentService paymentService ;

	@Autowired
	public PaymentsController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping("/getall")
	DataResult<List<ListPaymentDto>> getAll(){
		return this.paymentService.getAll();
	}
	
	@PostMapping("/add")
	Result add(@RequestParam boolean rememberMe,@RequestBody @Valid CreatePaymentRequest createPaymentRequest) throws BusinessException {
		return this.paymentService.add(rememberMe,createPaymentRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody DeletePaymentRequest deletePaymentRequest) throws BusinessException {
		return this.paymentService.delete(deletePaymentRequest);
	}
	
}
