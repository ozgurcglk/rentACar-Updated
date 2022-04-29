package com.RentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CarMaintenanceService;
import com.RentACar.business.dtos.ListCarMaintenanceDto;
import com.RentACar.business.requests.CarMaintenanceRequests.CreateCarMaintenanceRequest;
import com.RentACar.business.requests.CarMaintenanceRequests.DeleteCarMaintenanceRequest;
import com.RentACar.business.requests.CarMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.RentACar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private final CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		
		var result = this.carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response = result.stream().map(carMaintenance -> this.modelMapperService
				.forDto().map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}
	
	@Override
	public DataResult<ListCarMaintenanceDto> getById(int carMaintenanceId) throws BusinessException {
		
		checkIfCarMaintenanceIdExists(carMaintenanceId);
		
		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(carMaintenanceId);
		ListCarMaintenanceDto result = this.modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class);
		return new SuccessDataResult<ListCarMaintenanceDto>(result);
	}
	
	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {
		
		checkIfCarIsOnMaintenance(createCarMaintenanceRequest.getCarId());
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);		
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.ADDED);
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {

		checkIfCarMaintenanceIdExists(deleteCarMaintenanceRequest.getCarMaintenanceId());
		
		this.carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getCarMaintenanceId());
			
		return new SuccessResult(Messages.DELETED);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
	
		checkIfCarMaintenanceIdExists(updateCarMaintenanceRequest.getCarMaintenanceId());	
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.UPDATED);

	}

	
	
	private boolean checkIfCarMaintenanceIdExists(int carMaintenanceId) throws BusinessException {
		if (this.carMaintenanceDao.getById(carMaintenanceId)== null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return true;
		}
	}
	
	private void checkIfCarIsOnMaintenance(int carId) throws BusinessException {
		List<CarMaintenance> carMaintenances = this.carMaintenanceDao.getByCar_CarId(carId);
			
		for (CarMaintenance carMaintenance: carMaintenances) {
				if(carMaintenance.getReturnDate() == null) {
					throw new BusinessException(Messages.NOTAVAILABLEFORNOW);
				}	
		}
	}
	
	@Override
	public void checkIfCarIsAvailableToRent(int carId, LocalDate rentDate) throws BusinessException {
	
		List<CarMaintenance> carMaintenances = this.carMaintenanceDao.getByCar_CarId(carId);
		if(!carMaintenances.isEmpty()) {
			for (CarMaintenance carMaintenance : carMaintenances) {
				if(carMaintenance.getReturnDate().isAfter(rentDate) || carMaintenance.getReturnDate()==null) {
					throw new BusinessException(Messages.NOTAVAILABLEFORNOW);
					
				}
			}
		}
	
	}
	

}
