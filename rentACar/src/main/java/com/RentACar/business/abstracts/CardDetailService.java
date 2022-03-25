package com.RentACar.business.abstracts;

import java.util.List;

import com.RentACar.business.dtos.ListCardDetailDto;
import com.RentACar.business.requests.CreateCardDetailRequest;
import com.RentACar.business.requests.DeleteCardDetailRequest;
import com.RentACar.business.requests.UpdateCardDetailRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface CardDetailService {

		Result add(CreateCardDetailRequest createCardDetailRequest) throws BusinessException;

		DataResult<List<ListCardDetailDto>> getAll();
		
		DataResult<List<ListCardDetailDto>> getAllByCustomerId(int customerId);
		
		Result update(UpdateCardDetailRequest updateCardDetailRequest) throws BusinessException;
		
		Result delete(DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException;
		
}
