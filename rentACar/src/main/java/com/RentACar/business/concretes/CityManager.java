package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CityService;
import com.RentACar.business.dtos.ListCityDto;
import com.RentACar.business.requests.CreateCityRequest;
import com.RentACar.business.requests.DeleteCityRequest;
import com.RentACar.business.requests.UpdateCityRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CityDao;
import com.RentACar.entities.concretes.City;

@Service
public class CityManager implements CityService{
	
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) throws BusinessException {
		
		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		
		return new SuccessResult("New.City.Successfully.Added");
	}

	@Override
	public DataResult<List<ListCityDto>> getAll() {
		
		var result = this.cityDao.findAll();
		
		List<ListCityDto> response = result.stream().map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(response);
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException {
		
		City city = this.modelMapperService.forRequest().map(deleteCityRequest, City.class);
		this.cityDao.delete(city);

		return new SuccessResult("City.Deleted");
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) throws BusinessException {
		
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityDao.save(city);

		return new SuccessResult("City.Updated");
	}
	
	
	
}
