package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.AdditionalServiceService;
import com.RentACar.business.dtos.ListAdditionalServiceDto;
import com.RentACar.business.requests.AdditionalServiceRequests.CreateAdditionalServiceRequest;
import com.RentACar.business.requests.AdditionalServiceRequests.DeleteAdditionalServiceRequest;
import com.RentACar.business.requests.AdditionalServiceRequests.UpdateAdditionalServiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
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
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao,
			 ModelMapperService modelMapperService) {
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getAll() {
		
		var result = this.additionalServiceDao.findAll();
		
		List<ListAdditionalServiceDto> response = result.stream()
				.map(additionalService->this.modelMapperService
						.forDto().map(additionalService, ListAdditionalServiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		
		AdditionalService additionalService = this.modelMapperService
				.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessResult(Messages.ADDED);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
		
		checkIfAdditionalServiceIdExists(updateAdditionalServiceRequest.getServiceId());

		AdditionalService additionalService = this.additionalServiceDao.getByServiceId(updateAdditionalServiceRequest.getServiceId());
		forUpdate(additionalService, updateAdditionalServiceRequest);
		this.additionalServiceDao.save(additionalService);	
		
		return new SuccessResult(Messages.UPDATED);
		
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		
		checkIfAdditionalServiceIdExists(deleteAdditionalServiceRequest.getServiceId());

		AdditionalService additionalService = this.modelMapperService.forRequest().map(deleteAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.delete(additionalService);
		return new SuccessResult(Messages.DELETED);
		
	}

	@Override
	public DataResult<ListAdditionalServiceDto> getByServiceId(int serviceId) throws BusinessException {

		AdditionalService additionalService = checkIfAdditionalServiceIdExists(serviceId);
		ListAdditionalServiceDto response = this.modelMapperService.forDto().map(additionalService, ListAdditionalServiceDto.class);
		return new SuccessDataResult<ListAdditionalServiceDto>(response);
		
	}
	
	private void forUpdate(AdditionalService additionalService, UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		
		additionalService.setServiceName(updateAdditionalServiceRequest.getServiceName());
		
		additionalService.setServiceDescription(updateAdditionalServiceRequest.getServiceDescription());
		
		additionalService.setServicePrice(updateAdditionalServiceRequest.getServicePrice());
		
		
	}
	
	private AdditionalService checkIfAdditionalServiceIdExists(int serviceId) throws BusinessException {
		
		AdditionalService additionalService = this.additionalServiceDao.getByServiceId(serviceId);
		if (additionalService == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return additionalService;
		}	
	}
	
}
