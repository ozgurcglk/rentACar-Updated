package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.dtos.BrandListDto;
import com.RentACar.business.requests.CreateBrandRequest;
import com.RentACar.business.requests.DeleteBrandRequest;
import com.RentACar.business.requests.UpdateBrandRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.BrandDao;
import com.RentACar.business.abstracts.BrandService;
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
	public List<BrandListDto> getAll() {
		var result = this.brandDao.findAll();
		List<BrandListDto> response = result.stream()
				.map(brand -> this.modelMapperService.forDto().map(brand, BrandListDto.class))
				.collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		if (checkBrandNames(brand)) {
			this.brandDao.save(brand);
		}

	}

	private boolean checkBrandNames(Brand brand) throws BusinessException {

		// for (Brand brands : brandDao.findAll()) {
		var result = this.brandDao.getByBrandName(WordUtils.capitalize(brand.getBrandName()));
		if (result == null) {
			return true;
		} else {
			throw new BusinessException("Bu marka var.");
		}
		// }

	}

	@Override
	public BrandListDto getById(int brandId) throws BusinessException {
		var result = this.brandDao.getByBrandId(brandId);
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir marka bulunmuyor.");
		} else {
			BrandListDto response = this.modelMapperService.forDto().map(result, BrandListDto.class);
			return response;
		}
	}

	private boolean checkBrandId(Brand brand) throws BusinessException {
		var result = this.brandDao.getByBrandId(brand.getBrandId());
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir marka bulunmuyor.");
		} else {
			return true;
		}
	}

	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		var result = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		if (checkBrandId(result)) {
			this.brandDao.delete(result);
		}

	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		var result = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		if (checkBrandId(result) && checkBrandNames(result)) {
			this.brandDao.save(result);
		}

	}

}
