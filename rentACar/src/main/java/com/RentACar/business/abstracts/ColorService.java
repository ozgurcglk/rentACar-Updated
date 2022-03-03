package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ColorListDto;
import com.RentACar.business.requests.CreateColorRequest;
import com.RentACar.business.requests.DeleteColorRequest;
import com.RentACar.business.requests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface ColorService {
	DataResult<List<ColorListDto>> getAll();
	Result add(CreateColorRequest createColorRequest) throws BusinessException;
	DataResult<ColorListDto> getById(int colorId) throws BusinessException;
	
	Result delete (DeleteColorRequest deleteColorRequest) throws BusinessException;
	Result update(UpdateColorRequest updateColorRequest) throws BusinessException;
	
}
