package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListCorporateCustomerDto;
import com.RentACar.business.requests.CorporateCustomerRequests.CreateCorporateCustomerRequest;
import com.RentACar.business.requests.CorporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.RentACar.business.requests.CorporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface CorporateCustomerService {
	
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;
	
	DataResult<List<ListCorporateCustomerDto>> getAll();
	
	Result delete (DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;
	
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException;
	
}
