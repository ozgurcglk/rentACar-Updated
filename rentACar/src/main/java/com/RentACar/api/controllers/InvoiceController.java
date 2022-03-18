package com.RentACar.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.InvoiceService;
import com.RentACar.business.dtos.ListInvoiceDto;
import com.RentACar.business.requests.CreateInvoiceRequest;
import com.RentACar.business.requests.DeleteInvoiceRequest;
import com.RentACar.business.requests.UpdateInvoiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	private InvoiceService invoiceService;
	
	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ListInvoiceDto>> getAll(){
		return this.invoiceService.getAll();
	}
	
	@GetMapping("/getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual")
	DataResult<List<ListInvoiceDto>> getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate rentDate, LocalDate returnDate){
		return this.invoiceService.getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(rentDate, returnDate);
	}

	@GetMapping("/getByCustomers_userId")
	DataResult<List<ListInvoiceDto>> getByCustomers_userId(int userId){
		return this.invoiceService.getByCustomers_userId(userId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest ) throws BusinessException{
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateInvoiceRequest updateInvoiceRequest ) throws BusinessException{
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteInvoiceRequest deleteInvoiceRequest ) throws BusinessException{
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
	
}
