package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CarMaintenanceService;
import com.RentACar.business.dtos.ListCarMaintenanceDto;
import com.RentACar.business.requests.CreateCarMaintenanceRequest;
import com.RentACar.business.requests.DeleteCarMaintenanceRequest;
import com.RentACar.business.requests.UpdateCarMaintenanceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.ErrorDataResult;
import com.RentACar.core.results.ErrorResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CarDao;
import com.RentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.RentACar.dataAccess.abstracts.RentalDao;
import com.RentACar.entities.concretes.CarMaintenance;
import com.RentACar.entities.concretes.Rental;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private final CarMaintenanceDao carMaintenanceDao;
	private final RentalDao rentalDao;
	private final CarDao carDao;
	private ModelMapperService modelMapperService;
	
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, RentalDao rentalDao, CarDao carDao, ModelMapperService modelMapperService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.rentalDao = rentalDao;
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		var result = this.carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response = result.stream()
				.map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		if(checkIfRequestedCarIsOnRental(carMaintenance)) {
			this.carMaintenanceDao.save(carMaintenance);
			return new SuccessResult("New.Car.Maintenance.Added");
		} 
		return new ErrorResult();
	}

	@Override
	public DataResult<ListCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId) throws BusinessException {
		var result = this.carMaintenanceDao.getByCarMaintenanceId(carMaintenanceId);
		if (checkCarMaintenanceId(result)) {
			
			ListCarMaintenanceDto response = this.modelMapperService.forDto().map(result, ListCarMaintenanceDto.class);
			return new SuccessDataResult<ListCarMaintenanceDto>(response);
			
		} else {
			return new ErrorDataResult<ListCarMaintenanceDto>("No.Such.As.Maintenance.Found");
		}
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
		if (checkCarMaintenanceId(carMaintenance)) {
			this.carMaintenanceDao.delete(carMaintenance);
			return new SuccessResult("Car.Maintenance.Deleted");
		}
		else {
			return new ErrorResult("No.Such.As.Maintenance.Found");
		}
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		if (checkCarMaintenanceId(carMaintenance)) {
			this.carMaintenanceDao.save(carMaintenance);
			return new SuccessResult("Car.Maintenance.Updated");
		}
		else {
			return new ErrorResult("No.Such.As.Maintenance.Found");
		}
	}

	
	
	private boolean checkCarMaintenanceId(CarMaintenance carMaintenance) throws BusinessException {
		var result = this.carMaintenanceDao.getByCarMaintenanceId(carMaintenance.getCarMaintenanceId());
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir servis aksiyonu bulunmuyor.");
		} else {
			return true;
		}
	}
	
	private boolean checkIfRequestedCarIsOnRental(CarMaintenance carMaintenance) {
		List<Rental> result = this.rentalDao.getByCarId(carMaintenance.getCarId());
		if(result != null) {
			for (Rental rental : result) {
				if(rental.getReturnDate() == null) {
					return false;
				}	
			}
			
		} return true;
	}
}
