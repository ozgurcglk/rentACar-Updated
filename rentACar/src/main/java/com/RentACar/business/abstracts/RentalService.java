package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.CreateRentalRequest;
import com.RentACar.business.requests.DeleteRentalRequest;
import com.RentACar.business.requests.UpdateRentalRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface RentalService {
	DataResult<List<ListRentalDto>> getAll();
	Result add(CreateRentalRequest createRentalRequest) throws BusinessException;
	Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException;
	Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException;
	DataResult<ListRentalDto> getByRentalId(int rentalId) throws BusinessException;
}
