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

import com.RentACar.business.abstracts.ColorService;
import com.RentACar.business.dtos.ListColorDto;
import com.RentACar.business.requests.ColorRequests.CreateColorRequest;
import com.RentACar.business.requests.ColorRequests.DeleteColorRequest;
import com.RentACar.business.requests.ColorRequests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;


@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	private ColorService colorService;
	
	@Autowired
	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListColorDto>> getAll(){
		return this.colorService.getAll();
	}
	
	@GetMapping("/getid")
    public DataResult<ListColorDto> getById(@RequestParam @Valid int colorId) throws BusinessException {
    	return this.colorService.getById(colorId);
    }
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) throws BusinessException{
		return this.colorService.add(createColorRequest);	
	}
	
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteColorRequest deleteColorRequest) throws BusinessException{
    	return this.colorService.delete(deleteColorRequest);
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws BusinessException{
    	return this.colorService.update(updateColorRequest);
    }

}