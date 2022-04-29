package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListColorDto;
import com.RentACar.business.requests.ColorRequests.CreateColorRequest;
import com.RentACar.business.requests.ColorRequests.DeleteColorRequest;
import com.RentACar.business.requests.ColorRequests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface ColorService {
	
	Result add(CreateColorRequest createColorRequest) throws BusinessException;

	DataResult<List<ListColorDto>> getAll();
	DataResult<ListColorDto> getById(int colorId) throws BusinessException;
	
	Result delete (DeleteColorRequest deleteColorRequest) throws BusinessException;
	Result update(UpdateColorRequest updateColorRequest) throws BusinessException;
	
}
