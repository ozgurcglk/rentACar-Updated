package com.RentACar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.RentACar.business.dtos.ListInvoiceDto;
import com.RentACar.business.requests.InvoiceRequests.CreateInvoiceRequest;
import com.RentACar.business.requests.InvoiceRequests.DeleteInvoiceRequest;
import com.RentACar.business.requests.InvoiceRequests.UpdateInvoiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.entities.concretes.Invoice;

public interface InvoiceService {
	
	Invoice add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;
	
	DataResult<List<ListInvoiceDto>> getAll();
	DataResult<List<ListInvoiceDto>> getByDateOfBetween(LocalDate rentDate, LocalDate returnDate);
	DataResult<List<ListInvoiceDto>> getByCustomers_customerId(int customerId);
	
	Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;

}
