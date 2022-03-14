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

import com.RentACar.business.abstracts.CityService;
import com.RentACar.business.dtos.ListCityDto;
import com.RentACar.business.requests.CreateCityRequest;
import com.RentACar.business.requests.DeleteCityRequest;
import com.RentACar.business.requests.UpdateCityRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	
	private CityService cityService;

	@Autowired
	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) throws BusinessException{
		return this.cityService.add(createCityRequest);	
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListCityDto>> getAll(){
		return this.cityService.getAll();
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCityRequest deleteCityRequest) throws BusinessException{
    	return this.cityService.delete(deleteCityRequest);
    }
	
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) throws BusinessException{
    	return this.cityService.update(updateCityRequest);
    }
	
}
