package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.BrandListDto;
import com.RentACar.business.requests.CreateBrandRequest;
import com.RentACar.business.requests.DeleteBrandRequest;
import com.RentACar.business.requests.UpdateBrandRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface BrandService {

	DataResult<List<BrandListDto>> getAll();
	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;
	DataResult<BrandListDto> getById(int brandId) throws BusinessException;
	
	Result delete (DeleteBrandRequest deleteBrandRequest) throws BusinessException;
	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
}
