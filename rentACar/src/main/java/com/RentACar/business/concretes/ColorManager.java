package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.ColorService;
import com.RentACar.business.dtos.ColorListDto;
import com.RentACar.business.requests.CreateColorRequest;
import com.RentACar.business.requests.DeleteColorRequest;
import com.RentACar.business.requests.UpdateColorRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.ErrorResult;
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
		// super();
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ColorListDto>> getAll() {
		var result = this.colorDao.findAll();
		List<ColorListDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ColorListDto>>(response);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		if (checkColorNames(color)) {
			this.colorDao.save(color);
			return new SuccessResult("Color added");
		}
		else {
			return new ErrorResult("Color can not added");
		}
	}

	private boolean checkColorNames(Color color) throws BusinessException {

		// for (Brand brands : brandDao.findAll()) {
		var result = this.colorDao.getByColorName(color.getColorName());
		if (result == null) {
			return true;
		} else {
			throw new BusinessException("Bu renk var.");
		}
	}

	@Override
	public DataResult<ColorListDto> getById(int colorId) throws BusinessException {
		var result = this.colorDao.getByColorId(colorId);
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir renk bulunmuyor.");
		} else {
			ColorListDto response = this.modelMapperService.forDto().map(result, ColorListDto.class);
			return new SuccessDataResult<ColorListDto>(response);
		}
	}

	private boolean checkColorId(Color color) throws BusinessException {
		var result = this.colorDao.getByColorId(color.getColorId());
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir renk bulunmuyor.");
		} else {
			return true;
		}
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		var result = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		if (checkColorId(result)) {
			this.colorDao.delete(result);
			return new SuccessResult("Color deleted");
		}
		else {
			return new ErrorResult("Color can not deleted");
		}

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {
		var result = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		if (checkColorId(result) && checkColorNames(result)) {
			this.colorDao.save(result);
			return new SuccessResult("Color updated");
		}
		else {
			return new ErrorResult("Colorcan not updated");
		}

	}
}
