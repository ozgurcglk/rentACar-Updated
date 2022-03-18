package com.RentACar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.RentACar.business.dtos.ListInvoiceDto;
import com.RentACar.business.requests.CreateInvoiceRequest;
import com.RentACar.business.requests.DeleteInvoiceRequest;
import com.RentACar.business.requests.UpdateInvoiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;

public interface InvoiceService {
	
	Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;
	DataResult<List<ListInvoiceDto>> getAll();
	DataResult<List<ListInvoiceDto>> getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate rentDate, LocalDate returnDate);
	DataResult<List<ListInvoiceDto>> getByCustomers_userId(int userId);
	Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;

}
