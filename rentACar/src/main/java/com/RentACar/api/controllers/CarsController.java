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
import com.RentACar.business.dtos.GetCarByPriceDto;
import com.RentACar.business.requests.CreateCarRequest;
import com.RentACar.business.requests.DeleteCarRequest;
import com.RentACar.business.requests.UpdateCarRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;
	
	@Autowired
	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CarListDto>> getAll(){
		return this.carService.getAll();
	}
	
	@GetMapping("/getid")
    public DataResult<CarListDto> getById(int carId) throws BusinessException {
    	return this.carService.getById(carId);
    }
	
	@GetMapping("/getAllPaged")
    public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {
    	return this.carService.getAllPaged(pageNo,pageSize);
    }
	
	@GetMapping("/getAllSorted"+" SortValue: 0=ASC -- 1=DESC ")
    public DataResult<List<CarListDto>> getAllSorted(int sortValue) throws BusinessException {
    	return this.carService.getAllSorted(sortValue);
    }
	
	@GetMapping("/getByDailyPrice")
    public DataResult<List<GetCarByPriceDto>> getCarByDailyPriceLessThanEqual(int dailyPrice) throws BusinessException {
    	return this.carService.getCarByDailyPriceLessThanEqual(dailyPrice);
    }
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCarRequest createCarRequest) throws BusinessException{
		return this.carService.add(createCarRequest);	
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) throws BusinessException{
    	return this.carService.delete(deleteCarRequest);
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException{
    	return this.carService.update(updateCarRequest);
    }

}
