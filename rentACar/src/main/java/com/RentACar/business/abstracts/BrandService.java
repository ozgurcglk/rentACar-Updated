package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.BrandListDto;
import com.RentACar.business.requests.CreateBrandRequest;
import com.RentACar.business.requests.DeleteBrandRequest;
import com.RentACar.business.requests.UpdateBrandRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.entities.concretes.Brand;

public interface BrandService {

	List<BrandListDto> getAll();
	void add(CreateBrandRequest createBrandRequest) throws BusinessException;
	BrandListDto getById(int brandId) throws BusinessException;
	
	void delete (DeleteBrandRequest deleteBrandRequest) throws BusinessException;
	void update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
}
