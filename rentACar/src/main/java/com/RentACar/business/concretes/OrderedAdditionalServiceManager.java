package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.AdditionalServiceService;
import com.RentACar.business.abstracts.OrderedAdditionalServiceService;
import com.RentACar.business.abstracts.RentalService;
import com.RentACar.business.dtos.ListAdditionalServiceDto;
import com.RentACar.business.dtos.ListOrderedAdditionalServiceDto;
import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.OrderedAdditionalServiceRequests.CreateOrderedAdditionalServiceRequest;
import com.RentACar.business.requests.OrderedAdditionalServiceRequests.UpdateOrderedAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.RentACar.entities.concretes.AdditionalService;
import com.RentACar.entities.concretes.OrderedAdditionalService;
import com.RentACar.entities.concretes.Rental;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService{

	private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
	private ModelMapperService modelMapperService;
	private AdditionalServiceService additionalServiceService;
	private RentalService rentalService;
	
	@Autowired
	@Lazy
	public OrderedAdditionalServiceManager(OrderedAdditionalServiceDao orderedAdditionalServiceDao,
			ModelMapperService modelMapperService, AdditionalServiceService additionalServiceService,
			RentalService rentalService) {
		this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.additionalServiceService = additionalServiceService;
		this.rentalService = rentalService;
	}

	@Override
	public Result add(List<Integer> serviceIds, int rentalId) throws BusinessException {
		
		for(Integer id : serviceIds) {
			checkIfAdditionalServiceExists(id);
		} 
		checkIfRentalExists(rentalId);
		addOperationMapping(serviceIds, rentalId);
		
		return new SuccessResult(Messages.ADDED);
	}
	


	@Override
	public DataResult<List<ListOrderedAdditionalServiceDto>> getAll() {
		
		List<OrderedAdditionalService> result = this.orderedAdditionalServiceDao.findAll();
		List<ListOrderedAdditionalServiceDto> response = result.stream().map(orderedAdditionalService -> this.modelMapperService
				.forDto().map(orderedAdditionalService,ListOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(response);
	
	}

	@Override
	public Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException {
	
		checkIfOrderedAdditionalServiceExists(updateOrderedAdditionalServiceRequest.getOrderedAdditionalServiceId());
		OrderedAdditionalService orderedAdditionalService = this.orderedAdditionalServiceDao.getByOrderedAdditionalServiceId(updateOrderedAdditionalServiceRequest
				.getOrderedAdditionalServiceId());
		updateOperationMapping(orderedAdditionalService, updateOrderedAdditionalServiceRequest);
		this.orderedAdditionalServiceDao.save(orderedAdditionalService);
		return new SuccessResult(Messages.UPDATED);
		
	}
	
	@Override
	public DataResult<List<ListOrderedAdditionalServiceDto>> getOrderedAdditionalServiceByRentalId(int rentalId) {
		
		List<OrderedAdditionalService> result = this.orderedAdditionalServiceDao.getByRental_RentalId(rentalId);
		List<ListOrderedAdditionalServiceDto> response = result.stream().map(orderedAdditionalService -> this.modelMapperService.forDto().map(orderedAdditionalService, ListOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(response);
		
	}
		
	private Rental checkIfRentalExists(int rentalId) throws BusinessException {
		
		ListRentalDto listRentalDto = this.rentalService.getById(rentalId).getData();
	
		Rental rental = this.modelMapperService.forDto().map(listRentalDto, Rental.class);
		return rental;
	}

	private OrderedAdditionalService checkIfOrderedAdditionalServiceExists(int orderedAdditionalServiceId) throws BusinessException {
	
		OrderedAdditionalService orderedAdditionalService = this.orderedAdditionalServiceDao.getByOrderedAdditionalServiceId(orderedAdditionalServiceId);
		if(orderedAdditionalService == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return orderedAdditionalService;
		}
	
	}
	
	private AdditionalService checkIfAdditionalServiceExists(int serviceId) throws BusinessException {
		
		ListAdditionalServiceDto listAdditionalServiceDto = this.additionalServiceService.getByServiceId(serviceId).getData();
		AdditionalService additionalService = this.modelMapperService.forDto().map(listAdditionalServiceDto, AdditionalService.class);
		return additionalService;
	}
	private void addOperationMapping(List<Integer> serviceIds, int rentalId) {
		for(int additionalService : serviceIds) {
			CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest = new CreateOrderedAdditionalServiceRequest();
			createOrderedAdditionalServiceRequest.setRentalId(rentalId);
			createOrderedAdditionalServiceRequest.setServiceId(additionalService);
		
			OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forDto().map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
			orderedAdditionalService.setOrderedAdditionalServiceId(0);
			this.orderedAdditionalServiceDao.save(orderedAdditionalService);
		}
	}
	
	private void updateOperationMapping(OrderedAdditionalService orderedAdditionalService, UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = checkIfAdditionalServiceExists(updateOrderedAdditionalServiceRequest.getServiceId());
		
		Rental rental = checkIfRentalExists(updateOrderedAdditionalServiceRequest.getRentalId());
		
		orderedAdditionalService.setAdditionalService(additionalService);
		orderedAdditionalService.setRental(rental);
	}


	
}
