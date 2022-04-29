package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListBrandDto;
import com.RentACar.business.requests.BrandRequests.CreateBrandRequest;
import com.RentACar.business.requests.BrandRequests.DeleteBrandRequest;
import com.RentACar.business.requests.BrandRequests.UpdateBrandRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface BrandService {

	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;

	DataResult<List<ListBrandDto>> getAll();
	DataResult<ListBrandDto> getById(int brandId) throws BusinessException;
	
	Result delete (DeleteBrandRequest deleteBrandRequest) throws BusinessException;
	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
}
