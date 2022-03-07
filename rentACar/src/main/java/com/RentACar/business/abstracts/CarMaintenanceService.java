package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListCarMaintenanceDto;
import com.RentACar.business.requests.CreateCarMaintenanceRequest;
import com.RentACar.business.requests.DeleteCarMaintenanceRequest;
import com.RentACar.business.requests.UpdateCarMaintenanceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface CarMaintenanceService {
	DataResult<List<ListCarMaintenanceDto>> getAll();
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;
	DataResult<ListCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId) throws BusinessException;
	Result delete (DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException;
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException;

}
