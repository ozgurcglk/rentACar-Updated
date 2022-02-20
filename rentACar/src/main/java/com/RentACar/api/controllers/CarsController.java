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

import com.RentACar.business.abstracts.CarService;
import com.RentACar.business.dtos.CarListDto;
import com.RentACar.business.requests.CreateCarRequest;
import com.RentACar.business.requests.DeleteCarRequest;
import com.RentACar.business.requests.DeleteColorRequest;
import com.RentACar.business.requests.UpdateCarRequest;
import com.RentACar.business.requests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;
	
	@Autowired
	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping("/getall")
	public List<CarListDto> getAll(){
		return this.carService.getAll();
	}
	
	@GetMapping("/getid")
    public CarListDto getById(int carId) throws BusinessException {
    	return this.carService.getById(carId);
    }
	
	@PostMapping("/add")
	public void add(@RequestBody CreateCarRequest createCarRequest) throws BusinessException{
		this.carService.add(createCarRequest);	
	}
	
    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteCarRequest deleteCarRequest) throws BusinessException{
    this.carService.delete(deleteCarRequest);
    }
    
    @PutMapping("/update")
    public void update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException{
    this.carService.update(updateCarRequest);
    }

}
