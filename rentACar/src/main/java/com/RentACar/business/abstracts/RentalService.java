package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.RentalRequests.CreateRentalRequest;
import com.RentACar.business.requests.RentalRequests.DeleteRentalRequest;
import com.RentACar.business.requests.RentalRequests.UpdateRentalRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.entities.concretes.Rental;

public interface RentalService {
	
	Rental add(CreateRentalRequest createRentalRequest) throws BusinessException;

	DataResult<List<ListRentalDto>> getAll();
	DataResult<ListRentalDto> getById(int rentalId) throws BusinessException;

	Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException;
	Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException;
}
