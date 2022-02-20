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

import com.RentACar.business.abstracts.ColorService;
import com.RentACar.business.dtos.ColorListDto;
import com.RentACar.business.requests.CreateColorRequest;
import com.RentACar.business.requests.DeleteColorRequest;
import com.RentACar.business.requests.UpdateBrandRequest;
import com.RentACar.business.requests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;


@RestController
@RequestMapping("/api/colors")
public class ColorsController {

	private ColorService colorService;
	
	@Autowired
	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("/getall")
	public List<ColorListDto> getAll(){
		return this.colorService.getAll();
	}
	
	@GetMapping("/getid")
    public ColorListDto getById(int colorId) throws BusinessException {
    	return this.colorService.getById(colorId);
    }
	
	@PostMapping("/add")
	public void add(@RequestBody CreateColorRequest createColorRequest) throws BusinessException{
		this.colorService.add(createColorRequest);	
	}
	
    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteColorRequest deleteColorRequest) throws BusinessException{
    this.colorService.delete(deleteColorRequest);
    }
    
    @PutMapping("/update")
    public void update(@RequestBody UpdateColorRequest updateColorRequest) throws BusinessException{
    this.colorService.update(updateColorRequest);
    }

}