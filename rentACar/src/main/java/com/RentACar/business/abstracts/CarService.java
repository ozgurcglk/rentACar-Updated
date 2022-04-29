package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListCarDto;
import com.RentACar.business.dtos.GetCarByPriceDto;
import com.RentACar.business.requests.CarRequests.CreateCarRequest;
import com.RentACar.business.requests.CarRequests.DeleteCarRequest;
import com.RentACar.business.requests.CarRequests.UpdateCarRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;




public interface CarService {
	
	Result add(CreateCarRequest createCarRequest) throws BusinessException;
	
	DataResult<List<ListCarDto>> getAll();
	DataResult<ListCarDto> getById(int carId) throws BusinessException;
	DataResult<List<GetCarByPriceDto>> getCarByDailyPriceLessThanEqual(int dailyPrice);
	DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<List<ListCarDto>> getAllSorted(int sortValue);
	
	Result delete (DeleteCarRequest deleteCarRequest) throws BusinessException;
	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;
	
	
}
