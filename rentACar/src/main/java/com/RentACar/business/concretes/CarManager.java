package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CarService;
import com.RentACar.business.dtos.CarListDto;
import com.RentACar.business.requests.CreateCarRequest;
import com.RentACar.business.requests.DeleteCarRequest;
import com.RentACar.business.requests.UpdateCarRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CarDao;
import com.RentACar.entities.concretes.Car;


@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		// super();
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<CarListDto> getAll() {
		var result = this.carDao.findAll();
		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateCarRequest createCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);

	}

	@Override
	public CarListDto getById(int carId) throws BusinessException {
		var result = this.carDao.getByCarId(carId);
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir araba bulunmuyor.");
		} else {
			CarListDto response = this.modelMapperService.forDto().map(result, CarListDto.class);
			return response;
		}

	}

	private boolean checkCarId(Car car) throws BusinessException {
		var result = this.carDao.getByCarId(car.getCarId());
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir araba bulunmuyor.");
		} else {
			return true;
		}
	}

	@Override
	public void delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		if (checkCarId(car)) {
			this.carDao.delete(car);
		}

	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		if (checkCarId(car)) {
			this.carDao.save(car);
		}

	}

}
