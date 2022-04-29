package com.RentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.OrderedAdditionalServiceService;
import com.RentACar.business.dtos.ListOrderedAdditionalServiceDto;
import com.RentACar.business.requests.OrderedAdditionalServiceRequests.UpdateOrderedAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/ordered_additional_services")
public class OrderedAdditionalServicesController {
	
	private OrderedAdditionalServiceService orderedAdditionalServiceService;

	@Autowired
	public OrderedAdditionalServicesController(OrderedAdditionalServiceService orderedAdditionalServiceService) {
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListOrderedAdditionalServiceDto>> getAll() {
		return this.orderedAdditionalServiceService.getAll();
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException{
		return this.orderedAdditionalServiceService.update(updateOrderedAdditionalServiceRequest);
	}
	
	@GetMapping("/getOrderedAdditionalServiceByRentalId")
	DataResult<List<ListOrderedAdditionalServiceDto>> getOrderedAdditionalServiceByRentalId(@RequestParam int rentalId) throws BusinessException{
		return this.orderedAdditionalServiceService.getOrderedAdditionalServiceByRentalId(rentalId);
	}
	
}
