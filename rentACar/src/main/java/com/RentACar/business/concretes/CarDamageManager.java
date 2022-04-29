package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CarDamageService;
import com.RentACar.business.abstracts.CarService;
import com.RentACar.business.dtos.ListCarDamageDto;
import com.RentACar.business.requests.CarDamageRequests.CreateCarDamageRequest;
import com.RentACar.business.requests.CarDamageRequests.DeleteCarDamageRequest;
import com.RentACar.business.requests.CarDamageRequests.UpdateCarDamageRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CarDamageDao;
import com.RentACar.entities.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService {

	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	
	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, CarService carService ,ModelMapperService modelMapperService) {
		this.carDamageDao = carDamageDao;
		this.carService = carService;
		this.modelMapperService = modelMapperService;
		
	}

	@Override
	public DataResult<List<ListCarDamageDto>> getAll() {
		
		var result = this.carDamageDao.findAll();
		List<ListCarDamageDto> response = result.stream().map(carDamage -> this.modelMapperService
				.forDto().map(carDamage, ListCarDamageDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDamageDto>>(response);
	}
	
	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) throws BusinessException {
		
		checkIfCarExists(createCarDamageRequest.getCarId());
		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult(Messages.ADDED);
	}
	
	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) throws BusinessException {
		
		checkIfCarDamageExists(updateCarDamageRequest.getCarDamageId());
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult(Messages.UPDATED);
	}

	@Override
	public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) throws BusinessException {
		
		checkIfCarDamageExists(deleteCarDamageRequest.getCarDamageId());
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(deleteCarDamageRequest, CarDamage.class);
		this.carDamageDao.delete(carDamage);
		
		return new SuccessResult(Messages.DELETED);
	}
	
	private boolean checkIfCarExists(int carId) throws BusinessException {
		if(this.carService.getById(carId) == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return true;
		}
	}
	
	private boolean checkIfCarDamageExists(int carDamageId) throws BusinessException {
		if(this.carDamageDao.getById(carDamageId) == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return true;
		}
	}
	
	
}
