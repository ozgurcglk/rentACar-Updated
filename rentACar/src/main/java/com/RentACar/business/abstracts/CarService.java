package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.CarListDto;
import com.RentACar.business.requests.CreateCarRequest;
import com.RentACar.business.requests.DeleteCarRequest;
import com.RentACar.business.requests.UpdateCarRequest;
import com.RentACar.core.concretes.BusinessException;



public interface CarService {
	List<CarListDto> getAll();
	void add(CreateCarRequest createCarRequest) throws BusinessException;
	CarListDto getById(int carId) throws BusinessException;
	void delete (DeleteCarRequest deleteCarRequest) throws BusinessException;
	void update(UpdateCarRequest updateCarRequest) throws BusinessException;
	

}
