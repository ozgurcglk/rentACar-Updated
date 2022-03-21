package com.RentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.CarDamageService;
import com.RentACar.business.dtos.ListCarDamageDto;
import com.RentACar.business.requests.CreateCarDamageRequest;
import com.RentACar.business.requests.DeleteCarDamageRequest;
import com.RentACar.business.requests.UpdateCarDamageRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/car_damages")
public class CarDamageController {

	CarDamageService carDamageService;

	@Autowired
	public CarDamageController(CarDamageService carDamageService) {
		this.carDamageService = carDamageService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCarDamageRequest createCarDamageRequest) throws BusinessException{
		return this.carDamageService.add(createCarDamageRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListCarDamageDto>> getAll(){
		return this.carDamageService.getAll();
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCarDamageRequest updateCarDamageRequest ) throws BusinessException{
		return this.carDamageService.update(updateCarDamageRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCarDamageRequest deleteCarDamageRequest) throws BusinessException{
		return this.carDamageService.delete(deleteCarDamageRequest);
	}
	
}
