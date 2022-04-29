package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.BrandService;
import com.RentACar.business.dtos.ListBrandDto;
import com.RentACar.business.requests.BrandRequests.CreateBrandRequest;
import com.RentACar.business.requests.BrandRequests.DeleteBrandRequest;
import com.RentACar.business.requests.BrandRequests.UpdateBrandRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.BrandDao;
import com.RentACar.entities.concretes.Brand;


@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListBrandDto>> getAll() {
		
		var result = this.brandDao.findAll();
		List<ListBrandDto> response = result.stream().map(brand -> this.modelMapperService
				.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListBrandDto>>(response);
		
	}
	
	@Override
	public DataResult<ListBrandDto> getById(int brandId) throws BusinessException {
			
		Brand brand = checkIfBrandIdExists(brandId);
		ListBrandDto response = this.modelMapperService.forDto().map(brand, ListBrandDto.class);
		return new SuccessDataResult<ListBrandDto>(response);
	
	}
	
	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws BusinessException {
		
		checkIfBrandNameExists(createBrandRequest.getBrandName());
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.ADDED);		
	}



	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {

		var result = checkIfBrandIdExists(deleteBrandRequest.getBrandId());
		this.brandDao.delete(result);
		
		return new SuccessResult(Messages.DELETED);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		var result = checkIfBrandIdExists(updateBrandRequest.getBrandId());
		
		result = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(result);
		
		return new SuccessResult(Messages.UPDATED);
	}
	
	private boolean checkIfBrandNameExists(String brandName) throws BusinessException {

		var result = this.brandDao.getByBrandName(WordUtils.capitalize(brandName));
		if (result == null) {
			return true;
		} else {
			throw new BusinessException(Messages.EXISTS);
		}
	}

	private Brand checkIfBrandIdExists(int brandId) throws BusinessException {
		
		Brand brand = this.brandDao.getByBrandId(brandId);
		if (brand == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return brand;
		}
	}
	
}
