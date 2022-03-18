package com.RentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.InvoiceService;
import com.RentACar.business.dtos.ListInvoiceDto;
import com.RentACar.business.requests.CreateInvoiceRequest;
import com.RentACar.business.requests.DeleteInvoiceRequest;
import com.RentACar.business.requests.UpdateInvoiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.InvoiceDao;
import com.RentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService) {
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
	
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		
		return new SuccessResult("New.Invoice.Successfully.Created");
	
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAll() {
	
		var result = this.invoiceDao.findAll();
		List<ListInvoiceDto> response = result.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate rentDate,
			LocalDate returnDate) {
		
		var result = this.invoiceDao.findAll();
		List<ListInvoiceDto> response = result.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getByCustomers_userId(int userId) {
		
		var result = this.invoiceDao.findByCustomers_userId(userId);
		List<ListInvoiceDto> response = result.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
	
	Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
	this.invoiceDao.save(invoice);
	
	return new SuccessResult("Invoice.Successfully.Updated");
	
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
		
		Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
		this.invoiceDao.delete(invoice);
		
		return new SuccessResult("Invoice.Successfully.Deleted");
	}
	
	
	
}
