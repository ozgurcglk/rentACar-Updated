package com.RentACar.api.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentACar.business.abstracts.InvoiceService;
import com.RentACar.business.dtos.ListInvoiceDto;
import com.RentACar.business.requests.InvoiceRequests.CreateInvoiceRequest;
import com.RentACar.business.requests.InvoiceRequests.DeleteInvoiceRequest;
import com.RentACar.business.requests.InvoiceRequests.UpdateInvoiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.entities.concretes.Invoice;

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
	
	@GetMapping("/getByDatesBetween")
	DataResult<List<ListInvoiceDto>> getByDateOfBetween(@RequestParam LocalDate rentDate,@RequestParam LocalDate returnDate){
		return this.invoiceService.getByDateOfBetween(rentDate, returnDate);
	}

	@GetMapping("/getByCustomers_customerId")
	DataResult<List<ListInvoiceDto>> getByCustomers_customerId(@RequestParam @Valid int customerId){
		return this.invoiceService.getByCustomers_customerId(customerId);
	}

	@PostMapping("/add")
	public Invoice add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest ) throws BusinessException{
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest ) throws BusinessException{
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteInvoiceRequest deleteInvoiceRequest ) throws BusinessException{
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
	
}
