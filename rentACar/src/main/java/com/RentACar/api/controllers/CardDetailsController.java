package com.RentACar.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.CardDetailService;
import com.RentACar.business.dtos.ListCardDetailDto;
import com.RentACar.business.requests.CardDetailRequests.CreateCardDetailRequest;
import com.RentACar.business.requests.CardDetailRequests.DeleteCardDetailRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/card_details")
public class CardDetailsController {
	
	CardDetailService cardDetailService;

	@Autowired
	public CardDetailsController(CardDetailService cardDetailService) {
		this.cardDetailService = cardDetailService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCardDetailRequest createCardDetailRequest) throws BusinessException{
		return this.cardDetailService.add(createCardDetailRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<ListCardDetailDto> getById(@RequestParam @Valid int cardDetailId) throws BusinessException{
		return this.cardDetailService.getById(cardDetailId);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException{
		return this.cardDetailService.delete(deleteCardDetailRequest);
	}
	
}
