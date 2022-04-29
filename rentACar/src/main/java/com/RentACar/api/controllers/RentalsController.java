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

import com.RentACar.business.abstracts.RentalService;
import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.RentalRequests.CreateRentalRequest;
import com.RentACar.business.requests.RentalRequests.DeleteRentalRequest;
import com.RentACar.business.requests.RentalRequests.UpdateRentalRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.entities.concretes.Rental;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	private final RentalService rentalService;
	
	@Autowired
	public RentalsController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping("/getAll")
	public DataResult<List<ListRentalDto>> getAll(){
		return this.rentalService.getAll();
	}
	
	@GetMapping("/getByRentalId")
    public DataResult<ListRentalDto> getById(@RequestParam @Valid int rentalId) throws BusinessException {
    	return this.rentalService.getById(rentalId);
    }
	
	@PostMapping("/add")
	public Rental add(@RequestBody @Valid  CreateRentalRequest createRentalRequest) throws BusinessException{
		return this.rentalService.add(createRentalRequest);	
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest ) throws BusinessException{
    	return this.rentalService.delete(deleteRentalRequest);
    }
	
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) throws BusinessException{
    	return this.rentalService.update(updateRentalRequest);
    }
    
}
