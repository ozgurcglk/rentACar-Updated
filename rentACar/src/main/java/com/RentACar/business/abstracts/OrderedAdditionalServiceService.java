package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListOrderedAdditionalServiceDto;
import com.RentACar.business.requests.OrderedAdditionalServiceRequests.UpdateOrderedAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface OrderedAdditionalServiceService {
	
	Result add(List<Integer> serviceIds, int rentalId) throws BusinessException;
	
	DataResult<List<ListOrderedAdditionalServiceDto>> getAll();
	DataResult<List<ListOrderedAdditionalServiceDto>> getOrderedAdditionalServiceByRentalId(int rentalId);
	
	Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException;
	
}
