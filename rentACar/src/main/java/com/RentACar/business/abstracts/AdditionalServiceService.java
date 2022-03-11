package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListAdditionalServiceDto;
import com.RentACar.business.requests.CreateAdditionalServiceRequest;
import com.RentACar.business.requests.DeleteAdditionalServiceRequest;
import com.RentACar.business.requests.UpdateAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface AdditionalServiceService {
	DataResult<List<ListAdditionalServiceDto>> getAll();
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException;
	DataResult<ListAdditionalServiceDto> getByAdditionalServiceId(int serviceId) throws BusinessException;
}
