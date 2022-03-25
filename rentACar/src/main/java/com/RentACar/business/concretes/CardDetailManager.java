package com.RentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CardDetailService;
import com.RentACar.business.dtos.ListCardDetailDto;
import com.RentACar.business.requests.CreateCardDetailRequest;
import com.RentACar.business.requests.DeleteCardDetailRequest;
import com.RentACar.business.requests.UpdateCardDetailRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.CardDetailDao;
import com.RentACar.entities.concretes.CardDetail;

@Service
public class CardDetailManager implements CardDetailService {
	
	private CardDetailDao cardDetailDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CardDetailManager(CardDetailDao cardDetailDao, ModelMapperService modelMapperService) {
		this.cardDetailDao = cardDetailDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCardDetailRequest createCardDetailRequest) throws BusinessException {
		CardDetail cardDetail = this.modelMapperService.forRequest().map(createCardDetailRequest, CardDetail.class);
		this.cardDetailDao.save(cardDetail);
		
		return new SuccessResult(Messages.CARDADDED);
	}

	@Override
	public DataResult<List<ListCardDetailDto>> getAll() {
	
		var result = this.cardDetailDao.findAll();
		List<ListCardDetailDto> response = result.stream().map(cardDetail-> this.modelMapperService.forDto().map(cardDetail, ListCardDetailDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCardDetailDto>>(response);
		
	}

	@Override
	public DataResult<List<ListCardDetailDto>> getAllByCustomerId(int customerId) {
		
		var result = this.cardDetailDao.getByCustomer_CustomerId(customerId);
		
		List<ListCardDetailDto> response = result.stream().map(cardDetail-> this.modelMapperService.forDto().map(cardDetail, ListCardDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCardDetailDto>>(response);
	}

	@Override
	public Result update(UpdateCardDetailRequest updateCardDetailRequest) throws BusinessException {
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(updateCardDetailRequest, CardDetail.class);
		this.cardDetailDao.save(cardDetail);
		
		return new SuccessResult(Messages.CARDUPDATED);
		
	}

	@Override
	public Result delete(DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException {
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(deleteCardDetailRequest, CardDetail.class);
		this.cardDetailDao.delete(cardDetail);
		
		return new SuccessResult(Messages.CARDDELETED);
		
	}
		

}
