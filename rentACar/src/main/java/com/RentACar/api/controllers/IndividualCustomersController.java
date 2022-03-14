package com.RentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.IndividualCustomerService;
import com.RentACar.business.dtos.ListIndividualCustomerDto;
import com.RentACar.business.requests.CreateIndividualCustomerRequest;
import com.RentACar.business.requests.DeleteIndividualCustomerRequest;
import com.RentACar.business.requests.UpdateIndividualCustomerRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/individual_customers")
public class IndividualCustomersController {

	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		this.individualCustomerService = individualCustomerService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException{
		return this.individualCustomerService.add(createIndividualCustomerRequest);	
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListIndividualCustomerDto>> getAll(){
		return this.individualCustomerService.getAll();
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException{
    	return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
    }
	
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException{
    	return this.individualCustomerService.update(updateIndividualCustomerRequest);
    }
	
}
