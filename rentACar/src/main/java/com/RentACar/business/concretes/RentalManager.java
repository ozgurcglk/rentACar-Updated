package com.RentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CarMaintenanceService;
import com.RentACar.business.abstracts.CarService;
import com.RentACar.business.abstracts.CustomerService;
import com.RentACar.business.abstracts.RentalService;
import com.RentACar.business.dtos.ListCarDto;
import com.RentACar.business.dtos.ListCustomerDto;
import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.RentalRequests.CreateRentalRequest;
import com.RentACar.business.requests.RentalRequests.DeleteRentalRequest;
import com.RentACar.business.requests.RentalRequests.UpdateRentalRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.RentalDao;
import com.RentACar.entities.concretes.Customer;
import com.RentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarMaintenanceService carMaintenanceService;
	private CarService carService;
	private CustomerService customerService;
	
	@Autowired
	@Lazy
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,
			CarMaintenanceService carMaintenanceService, CarService carService, CustomerService customerService) {
		
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService = carService;
		this.customerService = customerService;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		var result = this.rentalDao.findAll();
		List<ListRentalDto> response = result.stream().map(rental-> this.modelMapperService
				.forDto().map(rental, ListRentalDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}


	@Override
	public Rental add(CreateRentalRequest createRentalRequest) throws BusinessException {
		
		checkIfCarExists(createRentalRequest.getCarId());
		checkIfCustomerExists(createRentalRequest.getCustomerId());
		carMaintenanceService.checkIfCarIsAvailableToRent(createRentalRequest.getCarId(), createRentalRequest.getRentDate());
		checkIfCarIsOnRental(createRentalRequest.getCarId(), createRentalRequest.getRentDate());
		
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		addOperationMapping(rental, createRentalRequest);
		this.rentalDao.save(rental);
		return rental;
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {
		
		checkIfRentalExists(updateRentalRequest.getRentalId());

		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.UPDATED);
		 
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		
		checkIfRentalExists(deleteRentalRequest.getRentalId());
		
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalDao.delete(rental);
		return new SuccessResult(Messages.DELETED);
		
	}
	
	@Override
	public DataResult<ListRentalDto> getById(int rentalId) throws BusinessException {
		
		Rental result = checkIfRentalExists(rentalId);
		ListRentalDto response = this.modelMapperService
				.forDto().map(result, ListRentalDto.class);
		return new SuccessDataResult<ListRentalDto>(response);
	}
	
	private boolean checkIfCarExists(int carId) throws BusinessException {
		if(this.carService.getById(carId) == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return true;
		}
	}
	
	private void checkIfCustomerExists(int customerId) throws BusinessException {
		this.customerService.getById(customerId);
	}
	
	private Customer customerCaster(int customerId) throws BusinessException {
		ListCustomerDto listCustomerDto = this.customerService.getById(customerId).getData();
		Customer customer = this.modelMapperService
				.forDto().map(listCustomerDto, Customer.class);
	
		return customer;
	}
	
	private	Rental checkIfRentalExists(int rentalId) throws BusinessException {
		Rental result = this.rentalDao.getByRentalId(rentalId);
		if (result == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return result;
		}	
	}

	private boolean checkIfCarIsOnRental(int carId, LocalDate rentDate) throws BusinessException {
		List<Rental> result = this.rentalDao.getByCar_CarId(carId);
		if(result == null) {
			return true;
		}
		for(Rental rental : result) {
			if(rentDate.isBefore(rental.getReturnDate())) {
				throw new BusinessException(Messages.NOTAVAILABLEFORNOW);
			}
		} return true;
	}
	
	private void addOperationMapping(Rental rental, CreateRentalRequest createRentalRequest) throws BusinessException {
		rental.setRentalId(0);
		rental.setTotalPrice(totalPriceCalc(rental));
		rental.setCustomer(customerCaster(createRentalRequest.getCustomerId()));
		rental.setRentMilage(this.carService.getById(createRentalRequest.getCarId()).getData().getMilage());
	}

	private double totalPriceCalc(Rental rental) throws BusinessException {
		
		ListCarDto listCarDto = this.carService.getById(rental.getCar().getCarId()).getData();
		
		long dateCount = ChronoUnit.DAYS.between(rental.getRentDate(),rental.getReturnDate());
		dateCount++;
		
		double rentalPrice = listCarDto.getDailyPrice();
		double totalRentalPrice = rentalPrice*dateCount;
		
		if(rental.getRentingCity().getCityId() != rental.getReturningCity().getCityId()) {
			// 750 sabit miktar
			return (totalRentalPrice = totalRentalPrice + 750);
		} else {
			return totalRentalPrice;
		}
	}
	
}
	


