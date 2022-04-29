package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListAdditionalServiceDto;
import com.RentACar.business.requests.AdditionalServiceRequests.CreateAdditionalServiceRequest;
import com.RentACar.business.requests.AdditionalServiceRequests.DeleteAdditionalServiceRequest;
import com.RentACar.business.requests.AdditionalServiceRequests.UpdateAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;
	
	DataResult<List<ListAdditionalServiceDto>> getAll();
	DataResult<ListAdditionalServiceDto> getByServiceId(int serviceId) throws BusinessException;
	
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException;
	
}
