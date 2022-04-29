package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CityService;
import com.RentACar.business.dtos.ListCityDto;
import com.RentACar.business.requests.CityRequests.CreateCityRequest;
import com.RentACar.business.requests.CityRequests.DeleteCityRequest;
import com.RentACar.business.requests.CityRequests.UpdateCityRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
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
		
		return new SuccessResult(Messages.ADDED);
	}

	@Override
	public DataResult<List<ListCityDto>> getAll() {
		
		var result = this.cityDao.findAll();
		
		List<ListCityDto> response = result.stream().map(city -> this.modelMapperService
				.forDto().map(city, ListCityDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(response);
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException {
		checkIfCityExists(deleteCityRequest.getCityId());
		City city = this.modelMapperService
				.forRequest().map(deleteCityRequest, City.class);
		this.cityDao.delete(city);

		return new SuccessResult(Messages.DELETED);
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) throws BusinessException {
		checkIfCityExists(updateCityRequest.getCityId());
		City city = this.modelMapperService
				.forRequest().map(updateCityRequest, City.class);
		this.cityDao.save(city);

		return new SuccessResult(Messages.UPDATED);
	}
	
	private void checkIfCityExists(int cityId) throws BusinessException{
		if(this.cityDao.getById(cityId) == null) {
			throw new BusinessException(Messages.NOTFOUND);
		}
	}
	
}
