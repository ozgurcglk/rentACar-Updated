package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.ColorService;
import com.RentACar.business.dtos.ListColorDto;
import com.RentACar.business.requests.ColorRequests.CreateColorRequest;
import com.RentACar.business.requests.ColorRequests.DeleteColorRequest;
import com.RentACar.business.requests.ColorRequests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.ColorDao;
import com.RentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListColorDto>> getAll() {
		
		var result = this.colorDao.findAll();
		List<ListColorDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(response);
	}
	
	@Override
	public DataResult<ListColorDto> getById(int colorId) throws BusinessException {
		
		Color color = checkIfColorIdExists(colorId);
		ListColorDto response = this.modelMapperService.forDto().map(color, ListColorDto.class);
		return new SuccessDataResult<ListColorDto>(response);
	}
	
	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		
		checkIfColorNameExists(createColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(Messages.ADDED);	
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		
		var result = checkIfColorIdExists(deleteColorRequest.getColorId());
		this.colorDao.delete(result);
		
		return new SuccessResult(Messages.DELETED);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {
		
		var result = checkIfColorIdExists(updateColorRequest.getColorId());  
		result = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(result);
		
		return new SuccessResult(Messages.UPDATED);

	}
	
	private boolean checkIfColorNameExists(String colorName) throws BusinessException {

		var result = this.colorDao.getByColorName(WordUtils.capitalize(colorName));
		if (result == null) {
			return true;
		} else {
			throw new BusinessException(Messages.EXISTS);
		}
	}
	
	private Color checkIfColorIdExists(int colorId) throws BusinessException {
		
		Color color = this.colorDao.getByColorId(colorId);
		if (color == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return color;
		}
	}
	
}
