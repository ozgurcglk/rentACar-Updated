package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.IndividualCustomerService;
import com.RentACar.business.dtos.ListIndividualCustomerDto;
import com.RentACar.business.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.RentACar.business.requests.IndividualCustomerRequests.DeleteIndividualCustomerRequest;
import com.RentACar.business.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.RentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
			ModelMapperService modelMapperService) {
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException {
		
		checkIfMailExists(createIndividualCustomerRequest.getEmail());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.ADDED);
		
	}

	@Override
	public DataResult<List<ListIndividualCustomerDto>> getAll() {
	
		var result = this.individualCustomerDao.findAll();
		List<ListIndividualCustomerDto> response = result.stream().map(individualCustomer -> this.modelMapperService.forDto().map(individualCustomer, ListIndividualCustomerDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListIndividualCustomerDto>>(response);
	
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException {
		
		checkIfIndividualCustomerExists(deleteIndividualCustomerRequest.getIndiCustomerId());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(deleteIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.delete(individualCustomer);
		
		return new SuccessResult(Messages.DELETED);
		
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException {
		
		checkIfIndividualCustomerExists(updateIndividualCustomerRequest.getIndiCustomerId());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.UPDATED);
		
	}
	
	private boolean checkIfMailExists(String eMail) throws BusinessException{
		
		IndividualCustomer individualCustomer = this.individualCustomerDao.findByEmail(eMail);
		if(individualCustomer != null ) {
			throw new BusinessException(Messages.EXISTS);
		} else {
			return true;
		}
		
	}
	
	private IndividualCustomer checkIfIndividualCustomerExists(int indiCustomerId) throws BusinessException{
		
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(indiCustomerId);
		if(individualCustomer == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return individualCustomer;
		}
	}
	
}
