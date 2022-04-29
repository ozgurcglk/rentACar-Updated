package com.RentACar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.RentACar.business.dtos.ListCarMaintenanceDto;
import com.RentACar.business.requests.CarMaintenanceRequests.CreateCarMaintenanceRequest;
import com.RentACar.business.requests.CarMaintenanceRequests.DeleteCarMaintenanceRequest;
import com.RentACar.business.requests.CarMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface CarMaintenanceService {
	
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;

	DataResult<List<ListCarMaintenanceDto>> getAll();
	DataResult<ListCarMaintenanceDto> getById(int carMaintenanceId) throws BusinessException;
	
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException;
	Result delete (DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException;

	public void checkIfCarIsAvailableToRent(int carId, LocalDate rentDate) throws BusinessException;
}
