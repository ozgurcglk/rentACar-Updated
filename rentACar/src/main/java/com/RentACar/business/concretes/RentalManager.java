package com.RentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.RentalService;
import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.CreateRentalRequest;
import com.RentACar.business.requests.DeleteRentalRequest;
import com.RentACar.business.requests.UpdateRentalRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.ErrorDataResult;
import com.RentACar.core.results.ErrorResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.RentACar.dataAccess.abstracts.RentalDao;
import com.RentACar.entities.concretes.CarMaintenance;
import com.RentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	
	private RentalDao rentalDao;
	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	ArrayList<CarMaintenance> carMaintenances= new ArrayList<CarMaintenance>();
	
	@Autowired
	public RentalManager(RentalDao rentalDao, CarMaintenanceDao carMaintenanceDao,
			ModelMapperService modelMapperService) {
		this.rentalDao = rentalDao;
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		var result = this.rentalDao.findAll();
		List<ListRentalDto> response = result.stream().map(rental-> this.modelMapperService.forDto().map(rental, ListRentalDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws BusinessException {
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		if(checkIfRequestedCarIsOnMaintenance(rental)) {
			this.rentalDao.save(rental);
			return new SuccessResult("Car.Successfully.Rented");
		} else {
			return new ErrorResult("Something.Went.Wrong");
		}
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		if (checkIfRentalIdExists(rental)) {
			this.rentalDao.save(rental);
			return new SuccessResult("Rental.Updated");
		} else {
			return new ErrorResult("No.Such.As.Rental.Found");
		}		 
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		if (checkIfRentalIdExists(rental)) {
			this.rentalDao.delete(rental);
			return new SuccessResult("Rental.Deleted");
		} else {
			return new ErrorResult("No.Such.As.Rental.Found");
		}
		
	}

	@Override
	public DataResult<ListRentalDto> getByRentalId(int rentalId) throws BusinessException {
		var result = this.rentalDao.getByRentalId(rentalId);
		if (checkIfRentalIdExists(result)) {
			ListRentalDto response = this.modelMapperService.forDto().map(result, ListRentalDto.class);
			return new SuccessDataResult<ListRentalDto>(response);
		} else {
			return new ErrorDataResult<ListRentalDto>("No.Such.As.Rental.Found");
		}
	}

	private boolean checkIfRentalIdExists(Rental rental) throws BusinessException {
		var result = this.rentalDao.getByRentalId(rental.getRentalId());
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir kiralanma işlemi bulunamadı.");
		} else {
			return true;
		}	
	}
	
	private boolean checkIfRequestedCarIsOnMaintenance(Rental rental) {
		List<CarMaintenance> result = this.carMaintenanceDao.getByCarId(rental.getCarId());
		if(result != null) {
			for (CarMaintenance carMaintenance : result) {
				if(carMaintenance.getReturnDate() == null) {
					return false;
				}	
			}
			
		} return true;
	}
	
//	private boolean checkIfRentedAndReturnCityIdIsEqual(int rentalId) throws BusinessException {
//		
//		int rentedCityId = rentalDao.getByRentalId(rentalId).getRentedCity().getId();
//		int returnCityId = rentalDao.getByRentalId(rentalId).getReturnCity().getId();
//		if(rentedCityId != returnCityId) {
//			return false;
//		}
//		return true;
//	}


}
	


