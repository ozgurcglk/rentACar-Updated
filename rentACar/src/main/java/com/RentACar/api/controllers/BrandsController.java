package com.RentACar.api.controllers;

import java.util.List;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.BrandService;
import com.RentACar.business.dtos.BrandListDto;
import com.RentACar.business.requests.CreateBrandRequest;
import com.RentACar.business.requests.DeleteBrandRequest;
import com.RentACar.business.requests.UpdateBrandRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.entities.concretes.Brand;


@RestController
@RequestMapping("/api/brands")
public class BrandsController {

	private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getall")
    public List<BrandListDto> getAll(){
        return this.brandService.getAll();
    }
    
    @GetMapping("/getid")
    public BrandListDto getById(int brandId) throws BusinessException {
    	return this.brandService.getById(brandId);
    }
    @PostMapping("/add")
    public void add(@RequestBody CreateBrandRequest createBrandRequest) throws BusinessException {
        this.brandService.add(createBrandRequest);
    }
    
    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) throws BusinessException{
    this.brandService.delete(deleteBrandRequest);
    }
    
    @PutMapping("/update")
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest) throws BusinessException{
    this.brandService.update(updateBrandRequest);
    }
    
}