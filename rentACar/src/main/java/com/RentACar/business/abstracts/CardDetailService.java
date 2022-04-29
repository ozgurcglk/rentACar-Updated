package com.RentACar.business.abstracts;

import com.RentACar.business.dtos.ListCardDetailDto;
import com.RentACar.business.requests.CardDetailRequests.CreateCardDetailRequest;
import com.RentACar.business.requests.CardDetailRequests.DeleteCardDetailRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface CardDetailService {

		Result add(CreateCardDetailRequest createCardDetailRequest) throws BusinessException;
		
		DataResult<ListCardDetailDto> getById(int cardDetailId) throws BusinessException;
				
		Result delete(DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException;
		
}
