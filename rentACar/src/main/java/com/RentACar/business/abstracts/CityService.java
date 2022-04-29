package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListCityDto;
import com.RentACar.business.requests.CityRequests.CreateCityRequest;
import com.RentACar.business.requests.CityRequests.DeleteCityRequest;
import com.RentACar.business.requests.CityRequests.UpdateCityRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface CityService {
	
	Result add(CreateCityRequest createCityRequest) throws BusinessException;
	
	DataResult<List<ListCityDto>> getAll();
	
	Result delete (DeleteCityRequest deleteCityRequest) throws BusinessException;
	
	Result update(UpdateCityRequest updateCityRequest) throws BusinessException;
	
}
