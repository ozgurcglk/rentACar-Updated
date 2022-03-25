package com.RentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.CardDetailService;
import com.RentACar.business.dtos.BrandListDto;
import com.RentACar.business.dtos.ListCarDamageDto;
import com.RentACar.business.dtos.ListCardDetailDto;
import com.RentACar.business.requests.CreateCarDamageRequest;
import com.RentACar.business.requests.CreateCardDetailRequest;
import com.RentACar.business.requests.DeleteCarDamageRequest;
import com.RentACar.business.requests.DeleteCardDetailRequest;
import com.RentACar.business.requests.UpdateCarDamageRequest;
import com.RentACar.business.requests.UpdateCardDetailRequest;
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
	public Result add(@RequestBody CreateCardDetailRequest createCardDetailRequest) throws BusinessException{
		return this.cardDetailService.add(createCardDetailRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListCardDetailDto>> getAll(){
		return this.cardDetailService.getAll();
	}
	
    @GetMapping("/getCardDetailByCustomerId")
    public DataResult<List<ListCardDetailDto>> getAllByCustomerId(int customerId) throws BusinessException {
    	return this.cardDetailService.getAllByCustomerId(customerId);
    }
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCardDetailRequest updateCardDetailRequest ) throws BusinessException{
		return this.cardDetailService.update(updateCardDetailRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCardDetailRequest deleteCardDetailRequest) throws BusinessException{
		return this.cardDetailService.delete(deleteCardDetailRequest);
	}
	
}
