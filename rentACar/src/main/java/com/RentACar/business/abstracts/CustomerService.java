package com.RentACar.business.abstracts;

import com.RentACar.business.dtos.ListCustomerDto;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;

public interface CustomerService {
	DataResult<ListCustomerDto> getById(int customerId) throws BusinessException;
}
