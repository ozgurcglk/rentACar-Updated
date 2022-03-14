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

import com.RentACar.business.abstracts.CorporateCustomerService;
import com.RentACar.business.dtos.ListCorporateCustomerDto;
import com.RentACar.business.requests.CreateCorporateCustomerRequest;
import com.RentACar.business.requests.DeleteCorporateCustomerRequest;
import com.RentACar.business.requests.UpdateCorporateCustomerRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/corporate_customers")
public class CorporateCustomersController {
	
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		this.corporateCustomerService = corporateCustomerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException{
		return this.corporateCustomerService.add(createCorporateCustomerRequest);	
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListCorporateCustomerDto>> getAll(){
		return this.corporateCustomerService.getAll();
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException{
    	return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
    }
	
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException{
    	return this.corporateCustomerService.update(updateCorporateCustomerRequest);
    }
	
}

