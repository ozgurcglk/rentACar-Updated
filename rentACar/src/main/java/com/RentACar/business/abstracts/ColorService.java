package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ColorListDto;
import com.RentACar.business.requests.CreateColorRequest;
import com.RentACar.business.requests.DeleteBrandRequest;
import com.RentACar.business.requests.DeleteColorRequest;
import com.RentACar.business.requests.UpdateBrandRequest;
import com.RentACar.business.requests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.entities.concretes.Color;

public interface ColorService {
	List<ColorListDto> getAll();
	void add(CreateColorRequest createColorRequest) throws BusinessException;
	ColorListDto getById(int colorId) throws BusinessException;
	
	void delete (DeleteColorRequest deleteColorRequest) throws BusinessException;
	void update(UpdateColorRequest updateColorRequest) throws BusinessException;
	
}
