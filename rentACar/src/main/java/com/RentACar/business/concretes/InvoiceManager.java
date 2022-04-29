package com.RentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentACar.business.abstracts.AdditionalServiceService;
import com.RentACar.business.abstracts.InvoiceService;
import com.RentACar.business.abstracts.OrderedAdditionalServiceService;
import com.RentACar.business.dtos.ListAdditionalServiceDto;
import com.RentACar.business.dtos.ListInvoiceDto;
import com.RentACar.business.dtos.ListOrderedAdditionalServiceDto;
import com.RentACar.business.requests.InvoiceRequests.CreateInvoiceRequest;
import com.RentACar.business.requests.InvoiceRequests.DeleteInvoiceRequest;
import com.RentACar.business.requests.InvoiceRequests.UpdateInvoiceRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
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
	private AdditionalServiceService  additionalServiceService;
	private OrderedAdditionalServiceService orderedAdditionalServiceService;
	
	
	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService,
			AdditionalServiceService additionalServiceService,
			OrderedAdditionalServiceService orderedAdditionalServiceService) {
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.additionalServiceService = additionalServiceService;
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
	}

	@Override
	public Invoice add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
	
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setInvoiceId(0);
		invoiceMappingOperations(invoice, createInvoiceRequest);	
		this.invoiceDao.save(invoice);
		
		return invoice;
	
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAll() {
	
		var result = this.invoiceDao.findAll();
		List<ListInvoiceDto> response = result.stream().map(invoice -> this.modelMapperService
				.forDto().map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
	
	checkIfInvoiceExists(updateInvoiceRequest.getInvoiceId());
	Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
	this.invoiceDao.save(invoice);
	
	return new SuccessResult(Messages.UPDATED);
	
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
		
		checkIfInvoiceExists(deleteInvoiceRequest.getInvoiceId());
		Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
		this.invoiceDao.delete(invoice);
		
		return new SuccessResult(Messages.DELETED);
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getByDateOfBetween(LocalDate rentDate, LocalDate returnDate) {
		
		List<Invoice> result = this.invoiceDao.findByCreateDateBetween(rentDate, returnDate);
		List<ListInvoiceDto> response = result.stream().map(invoice -> this.modelMapperService
				.forDto().map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getByCustomers_customerId(int customerId) {
		List<Invoice> result = this.invoiceDao.getByCustomer_CustomerId(customerId);
		List<ListInvoiceDto> response = result.stream().map(invoice -> this.modelMapperService
				.forDto().map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response);
	}
	
	private Invoice checkIfInvoiceExists(int invoiceId) throws BusinessException {
		Invoice invoice = this.invoiceDao.getByInvoiceId(invoiceId);
		if(invoice == null ) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return invoice;
		}
	}
	
	private void invoiceMappingOperations(Invoice invoice, CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
	
		invoice.setCreateDate(LocalDate.now());
		invoice.setRentDate(invoice.getRental().getRentDate());
		invoice.setReturnDate(invoice.getRental().getReturnDate());
		invoice.setTotalRentDay(dayCounter(invoice));
		invoice.setTotal(totalPriceCalc(invoice));
		
	}
	
	private int dayCounter (Invoice invoice) {
		Long dateCount = ChronoUnit.DAYS.between(invoice.getRentDate(), invoice.getReturnDate());
		int counter = dateCount.intValue()+1;
		return counter;
	}
	
	private double totalPriceCalc(Invoice invoice) throws BusinessException {
		
		double rentTotal = invoice.getRental().getTotalPrice();
		
		int dateCount = invoice.getTotalRentDay();
		
		double additionalServicePrice = 0;
		List<ListOrderedAdditionalServiceDto> listOrderedAdditionalServiceDtos = this.orderedAdditionalServiceService.getOrderedAdditionalServiceByRentalId(invoice.getRental().getRentalId()).getData();
		
		for(int i = 0; i < listOrderedAdditionalServiceDtos.size(); i++) {
			ListAdditionalServiceDto additionalServiceDto = this.additionalServiceService.getByServiceId(listOrderedAdditionalServiceDtos.get(i).getServiceId()).getData();
			additionalServicePrice += additionalServiceDto.getServicePrice();
		}
		
		double additionalServiceTotal = additionalServicePrice*dateCount;
		return (additionalServiceTotal  + rentTotal);
	}
	
	
	
	
}
