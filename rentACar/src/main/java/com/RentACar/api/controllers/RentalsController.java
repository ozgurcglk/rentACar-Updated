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

import com.RentACar.business.abstracts.RentalService;
import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.CreateRentalRequest;
import com.RentACar.business.requests.DeleteRentalRequest;
import com.RentACar.business.requests.UpdateRentalRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

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
    public DataResult<ListRentalDto> getByRentalId(int rentalId) throws BusinessException {
    	return this.rentalService.getByRentalId(rentalId);
    }
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid  CreateRentalRequest createRentalRequest) throws BusinessException{
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
