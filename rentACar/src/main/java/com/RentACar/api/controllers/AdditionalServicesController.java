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

import com.RentACar.business.abstracts.AdditionalServiceService;
import com.RentACar.business.dtos.ListAdditionalServiceDto;
import com.RentACar.business.requests.CreateAdditionalServiceRequest;
import com.RentACar.business.requests.DeleteAdditionalServiceRequest;
import com.RentACar.business.requests.UpdateAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/additional_services")
public class AdditionalServicesController {
	private final AdditionalServiceService additionalServiceService;

	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		this.additionalServiceService = additionalServiceService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListAdditionalServiceDto>> getAll(){
		return this.additionalServiceService.getAll();
	}
	
	@GetMapping("/getByAdditionalServiceId")
    public DataResult<ListAdditionalServiceDto> getByAdditionalServiceId(int serviceId) throws BusinessException {
    	return this.additionalServiceService.getByAdditionalServiceId(serviceId);
    }
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid  CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException{
		return this.additionalServiceService.add(createAdditionalServiceRequest);	
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteAdditionalServiceRequest deleteAdditionalServiceRequest ) throws BusinessException{
    	return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }
	
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException{
    	return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }
    
}
