package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListCarDamageDto;
import com.RentACar.business.requests.CreateCarDamageRequest;
import com.RentACar.business.requests.DeleteCarDamageRequest;
import com.RentACar.business.requests.UpdateCarDamageRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface CarDamageService {
	
	Result add(CreateCarDamageRequest createCarDamageRequest) throws BusinessException;
	
	DataResult<List<ListCarDamageDto>> getAll();

	Result update(UpdateCarDamageRequest updateCarDamageRequest) throws BusinessException;

	Result delete(DeleteCarDamageRequest deleteCarDamageRequest) throws BusinessException;
	
}
