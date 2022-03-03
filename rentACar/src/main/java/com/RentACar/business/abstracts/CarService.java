package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.CarListDto;
import com.RentACar.business.dtos.GetCarByPriceDto;
import com.RentACar.business.requests.CreateCarRequest;
import com.RentACar.business.requests.DeleteCarRequest;
import com.RentACar.business.requests.UpdateCarRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;




public interface CarService {
	DataResult<List<CarListDto>> getAll();
	Result add(CreateCarRequest createCarRequest) throws BusinessException;
	DataResult<CarListDto> getById(int carId) throws BusinessException;
	Result delete (DeleteCarRequest deleteCarRequest) throws BusinessException;
	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;
	DataResult<List<GetCarByPriceDto>> getCarByDailyPriceLessThanEqual(int dailyPrice);
	DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize);
	DataResult<List<CarListDto>> getAllSorted(int sortValue);
}
