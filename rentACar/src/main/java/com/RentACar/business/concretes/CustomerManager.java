package com.RentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CustomerService;
import com.RentACar.business.dtos.ListCustomerDto;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CustomerDao;
import com.RentACar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{
	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
		this.customerDao = customerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<ListCustomerDto> getById(int customerId) throws BusinessException {
		
		Customer result = checkIfCustomerExists(customerId);
		ListCustomerDto response = this.modelMapperService.forDto().map(result, ListCustomerDto.class);
		return new SuccessDataResult<ListCustomerDto>(response);
	}
	
	private Customer checkIfCustomerExists(int customerId) throws BusinessException {
		
		Customer customer = this.customerDao.getByCustomerId(customerId);
		if(customer == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return customer;
		}
			
	}
	
}
