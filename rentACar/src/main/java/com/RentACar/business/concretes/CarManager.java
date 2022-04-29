package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.BrandService;
import com.RentACar.business.abstracts.CarService;
import com.RentACar.business.abstracts.ColorService;
import com.RentACar.business.dtos.GetCarByPriceDto;
import com.RentACar.business.dtos.ListCarDto;
import com.RentACar.business.requests.CarRequests.CreateCarRequest;
import com.RentACar.business.requests.CarRequests.DeleteCarRequest;
import com.RentACar.business.requests.CarRequests.UpdateCarRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.ErrorDataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CarDao;
import com.RentACar.entities.concretes.Car;



@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private BrandService brandService;
	private ColorService colorService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, ColorService colorService, BrandService brandService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.colorService = colorService;
		this.brandService = brandService;
		
	}

	@Override
	public DataResult<List<ListCarDto>> getAll() {
		
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) throws BusinessException {
		
		checkIfBrandExists(createCarRequest.getBrandId());
		checklIfColorExists(createCarRequest.getColorId());
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.ADDED);

	}

	@Override
	public DataResult<ListCarDto> getById(int carId) throws BusinessException {
		
		Car result = checkCarId(carId);
		
		ListCarDto response = this.modelMapperService.forDto().map(result, ListCarDto.class);
		return new SuccessDataResult<ListCarDto>(response);

	}



	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		
		Car car = checkCarId(deleteCarRequest.getCarId());

		this.carDao.delete(car);
		return new SuccessResult(Messages.DELETED);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
		
		checkCarId(updateCarRequest.getCarId());
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		
		this.carDao.save(car);
		return new SuccessResult(Messages.UPDATED);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		List<Car> result=this.carDao.findAll(pageable).getContent();
		List<ListCarDto> response = result.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
			
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted(int sortValue) {
		if(sortValue==0) {
			Sort sort = Sort.by(Sort.Direction.ASC, "dailyPrice");
			List<Car> result = this.carDao.findAll(sort);
			List<ListCarDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
			return new SuccessDataResult<List<ListCarDto>>(response);
		}
		else if(sortValue==1) {
			Sort sort = Sort.by(Sort.Direction.DESC, "dailyPrice");
			List<Car> result = this.carDao.findAll(sort);
			List<ListCarDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
			return new SuccessDataResult<List<ListCarDto>>(response);
		}
		else {
			return new ErrorDataResult<List<ListCarDto>>(Messages.INVALIDVALUE);
		}
		
	}

	@Override
	public DataResult<List<GetCarByPriceDto>> getCarByDailyPriceLessThanEqual(int dailyPrice) {
		
		List<Car> result = this.carDao.getByDailyPriceLessThanEqual(dailyPrice);
		List<GetCarByPriceDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car, GetCarByPriceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetCarByPriceDto>>(response);
	}

	private Car checkCarId(int carId) throws BusinessException {
		Car result = this.carDao.getByCarId(carId);
		if (result == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return result;
		}
	}
	
	private boolean checkIfBrandExists(int brandId) throws BusinessException {
		if(this.brandService.getById(brandId) == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return true;
		}
	}
	
	private boolean checklIfColorExists(int colorId) throws BusinessException {
		if(this.colorService.getById(colorId)== null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return true;
		}
	}
	

}
