package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.AdditionalServiceService;
import com.RentACar.business.dtos.ListAdditionalServiceDto;
import com.RentACar.business.requests.CreateAdditionalServiceRequest;
import com.RentACar.business.requests.DeleteAdditionalServiceRequest;
import com.RentACar.business.requests.UpdateAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.ErrorDataResult;
import com.RentACar.core.results.ErrorResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.RentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		var result = this.additionalServiceDao.findAll();
		List<ListAdditionalServiceDto> response = result.stream().map(additionalService->this.modelMapperService.forDto().map(additionalService, ListAdditionalServiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult("New.Additional.Service.Added");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
		if(checkIfAdditionalServiceIdExists(additionalService)) {
			this.additionalServiceDao.save(additionalService);	
			return new SuccessResult("New.Additional.Service.Updated");
		}
		return new ErrorResult();
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(deleteAdditionalServiceRequest, AdditionalService.class);
		if(checkIfAdditionalServiceIdExists(additionalService)) {
			this.additionalServiceDao.delete(additionalService);
			return new SuccessResult("New.Additional.Service.Deleted");
		}
		return new ErrorResult();
	}

	@Override
	public DataResult<ListAdditionalServiceDto> getByAdditionalServiceId(int serviceId) throws BusinessException {
		var result = this.additionalServiceDao.getByServiceId(serviceId);
		if(checkIfAdditionalServiceIdExists(result)) {
			ListAdditionalServiceDto response = this.modelMapperService.forDto().map(result, ListAdditionalServiceDto.class);
			return new SuccessDataResult<ListAdditionalServiceDto>(response);
		} 
		return new ErrorDataResult<ListAdditionalServiceDto>("No.Such.As.ID");
	}
	
	private boolean checkIfAdditionalServiceIdExists(AdditionalService additionalService) throws BusinessException {
		var result = this.additionalServiceDao.getByServiceId(additionalService.getServiceId());
		if (result == null) {
			throw new BusinessException("Bu ID'ye sahip bir ek hizmet bulunamadı.");
		} else {
			return true;
		}	
	}
	
}
