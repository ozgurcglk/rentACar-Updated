package com.RentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.CardDetailService;
import com.RentACar.business.dtos.ListCardDetailDto;
import com.RentACar.business.requests.CardDetailRequests.CreateCardDetailRequest;
import com.RentACar.business.requests.CardDetailRequests.DeleteCardDetailRequest;
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
		
		checkIfCardNoExists(createCardDetailRequest.getCardNo());
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(createCardDetailRequest, CardDetail.class);
		this.cardDetailDao.save(cardDetail);
		
		return new SuccessResult(Messages.ADDED);
	}

	@Override
	public Result delete(DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException {
		
		checkIfCardExists(deleteCardDetailRequest.getCardDetailId());
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(deleteCardDetailRequest, CardDetail.class);
		this.cardDetailDao.delete(cardDetail);
		
		return new SuccessResult(Messages.DELETED);
		
	}
	
	@Override
	public DataResult<ListCardDetailDto> getById(int cardDetailId) throws BusinessException {
		
		CardDetail result = checkIfCardExists(cardDetailId);
		ListCardDetailDto response = this.modelMapperService
				.forDto().map(result, ListCardDetailDto.class);
		return new SuccessDataResult<ListCardDetailDto>(response);
	}
	
	private CardDetail checkIfCardExists(int cardDetailId) throws BusinessException {
		CardDetail cardDetail = this.cardDetailDao.getByCardDetailId(cardDetailId);
		if(cardDetail == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return cardDetail;
		}
	}
		
	private boolean checkIfCardNoExists(String cardNo) throws BusinessException{
		CardDetail cardDetail = this.cardDetailDao.existsByCardNo(cardNo);
		if(cardDetail == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return true;
		}
	}

	

}
