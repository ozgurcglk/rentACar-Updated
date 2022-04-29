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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.CarMaintenanceService;
import com.RentACar.business.dtos.ListCarMaintenanceDto;
import com.RentACar.business.requests.CarMaintenanceRequests.CreateCarMaintenanceRequest;
import com.RentACar.business.requests.CarMaintenanceRequests.DeleteCarMaintenanceRequest;
import com.RentACar.business.requests.CarMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/car_maintenances")
public class CarMaintenancesController {
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		this.carMaintenanceService = carMaintenanceService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListCarMaintenanceDto>> getAll(){
		return this.carMaintenanceService.getAll();
	}
	
	@GetMapping("/getById")
    public DataResult<ListCarMaintenanceDto> getById(@RequestParam @Valid int carMaintenanceId) throws BusinessException {
    	return this.carMaintenanceService.getById(carMaintenanceId);
    }
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException{
		return this.carMaintenanceService.add(createCarMaintenanceRequest);	
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException{
    	return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
    }
	
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException{
    	return this.carMaintenanceService.update(updateCarMaintenanceRequest);
    }
    
}
