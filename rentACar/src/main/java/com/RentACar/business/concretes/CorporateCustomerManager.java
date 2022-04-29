package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CorporateCustomerService;
import com.RentACar.business.dtos.ListCorporateCustomerDto;
import com.RentACar.business.requests.CorporateCustomerRequests.CreateCorporateCustomerRequest;
import com.RentACar.business.requests.CorporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.RentACar.business.requests.CorporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.RentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
		checkIfEmailExists(createCorporateCustomerRequest.getEmail());
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.ADDED);
		
	}

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getAll() {
		
		var result = this.corporateCustomerDao.findAll();
		List<ListCorporateCustomerDto> response = result.stream()
				.map(corporateCustomer -> this.modelMapperService.forDto().map(corporateCustomer, ListCorporateCustomerDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCorporateCustomerDto>>(response);
		
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
		
		checkIfCorporateCustomerExists(deleteCorporateCustomerRequest.getCorpCustomerId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(deleteCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.delete(corporateCustomer);
		
		return new SuccessResult(Messages.DELETED);
		
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {
		
		checkIfCorporateCustomerExists(updateCorporateCustomerRequest.getCorpCustomerId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.UPDATED);
		
	}
	
	private boolean checkIfEmailExists(String eMail) throws BusinessException {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.findByEmail(eMail);
		if(corporateCustomer != null) {
			throw new BusinessException(Messages.EXISTS);
		}else {
			return true;
		}
	}
	
	private CorporateCustomer checkIfCorporateCustomerExists(int corpCustomerId) throws BusinessException {
		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getByCorpCustomerId(corpCustomerId);
		if(corporateCustomer == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return corporateCustomer;
		}
		
	}
	
}
