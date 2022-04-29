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

import com.RentACar.business.abstracts.BrandService;
import com.RentACar.business.dtos.ListBrandDto;
import com.RentACar.business.requests.BrandRequests.CreateBrandRequest;
import com.RentACar.business.requests.BrandRequests.DeleteBrandRequest;
import com.RentACar.business.requests.BrandRequests.UpdateBrandRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;


@RestController
@RequestMapping("/api/brands")
public class BrandsController {

	private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getall")
    public DataResult<List<ListBrandDto>> getAll(){
        return this.brandService.getAll();
    }
    
    @GetMapping("/getid")
    public DataResult<ListBrandDto> getById(@RequestParam @Valid int brandId) throws BusinessException {
    	return this.brandService.getById(brandId);
    }
    @PostMapping("/add")
    public Result add(@RequestBody CreateBrandRequest createBrandRequest) throws BusinessException {
    	return this.brandService.add(createBrandRequest);
    }
    
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) throws BusinessException{
    	return this.brandService.delete(deleteBrandRequest);
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) throws BusinessException{
    	return this.brandService.update(updateBrandRequest);
    }
    
}