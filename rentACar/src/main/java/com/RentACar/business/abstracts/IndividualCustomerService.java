package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListIndividualCustomerDto;
import com.RentACar.business.requests.IndividualCustomerRequests.CreateIndividualCustomerRequest;
import com.RentACar.business.requests.IndividualCustomerRequests.DeleteIndividualCustomerRequest;
import com.RentACar.business.requests.IndividualCustomerRequests.UpdateIndividualCustomerRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface IndividualCustomerService {
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;
	
	DataResult<List<ListIndividualCustomerDto>> getAll();
	
	Result delete (DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;
	
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;
	
}
