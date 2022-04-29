package com.RentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RentACar.business.abstracts.CardDetailService;
import com.RentACar.business.abstracts.InvoiceService;
import com.RentACar.business.abstracts.OrderedAdditionalServiceService;
import com.RentACar.business.abstracts.PaymentService;
import com.RentACar.business.abstracts.PosService;
import com.RentACar.business.abstracts.RentalService;
import com.RentACar.business.dtos.ListPaymentDto;
import com.RentACar.business.dtos.ListRentalDto;
import com.RentACar.business.requests.CardDetailRequests.CreateCardDetailRequest;
import com.RentACar.business.requests.InvoiceRequests.CreateInvoiceRequest;
import com.RentACar.business.requests.PaymentRequests.CreateExtraDelayPaymentRequest;
import com.RentACar.business.requests.PaymentRequests.CreatePaymentRequest;
import com.RentACar.business.requests.PaymentRequests.DeletePaymentRequest;
import com.RentACar.core.concretes.BusinessException;
import com.RentACar.core.constants.Messages;
import com.RentACar.core.results.DataResult;
import com.RentACar.core.results.Result;
import com.RentACar.core.results.SuccessDataResult;
import com.RentACar.core.results.SuccessResult;
import com.RentACar.core.utilities.mapping.ModelMapperService;
import com.RentACar.dataAccess.abstracts.PaymentDao;
import com.RentACar.entities.concretes.Invoice;
import com.RentACar.entities.concretes.Payment;
import com.RentACar.entities.concretes.Rental;

import net.bytebuddy.utility.RandomString;

@Service
public class PaymentManager implements PaymentService{
	
	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private InvoiceService invoiceService;
	private OrderedAdditionalServiceService orderedAdditionalServiceService;
	private PosService posService;
	private CardDetailService cardDetailService;
	private RentalService rentalService;
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, InvoiceService invoiceService,
			OrderedAdditionalServiceService orderedAdditionalServiceService,
			PosService posService, CardDetailService cardDetailService, RentalService rentalService) {

		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.invoiceService = invoiceService;
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
		this.posService = posService;
		this.cardDetailService = cardDetailService;
		this.rentalService = rentalService;
	}

	@Transactional
	@Override
	public Result add(boolean rememberCard, CreatePaymentRequest createPaymentRequest) throws BusinessException {
		
		this.posService.doPayment(createPaymentRequest.getCreateCardDetailRequest().getCardHolder(),
				createPaymentRequest.getCreateCardDetailRequest().getCardNo(),
				createPaymentRequest.getCreateCardDetailRequest().getCardCvv());
		paymentProcessing(rememberCard, createPaymentRequest);
		return new SuccessResult(Messages.ADDED);
	}
	
	@Transactional
	@Override
	public Result addExtraDelayPayment(CreateExtraDelayPaymentRequest createExtraDelayPaymentRequest) throws BusinessException {
	
		this.posService.doPayment(createExtraDelayPaymentRequest.getCreateCardDetailRequest().getCardHolder()
				,createExtraDelayPaymentRequest.getCreateCardDetailRequest().getCardNo()
				,createExtraDelayPaymentRequest.getCreateCardDetailRequest().getCardCvv());
		
		extraDelayPaymentProcessing(createExtraDelayPaymentRequest);
	
		return new SuccessResult(Messages.ADDED);
	}

	@Override
	public DataResult<List<ListPaymentDto>> getAll() {
		
		var result = this.paymentDao.findAll();

		List<ListPaymentDto> response = result.stream().map(payment -> this.modelMapperService
				.forDto().map(payment, ListPaymentDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ListPaymentDto>>(response);
	}

	@Override
	public Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException {
		
		checkIfPaymentExists(deletePaymentRequest.getPaymentId());
		Payment payment = this.modelMapperService.forRequest().map(deletePaymentRequest, Payment.class);
		this.paymentDao.delete(payment);
		return new SuccessResult(Messages.DELETED);
		
	}
	
	
	private Payment checkIfPaymentExists(int paymentId) throws BusinessException {
		
		Payment payment = this.paymentDao.getByPaymentId(paymentId);
		if(payment == null) {
			throw new BusinessException(Messages.NOTFOUND);
		} else {
			return payment;
		}
		
	}	
	
	private void saveCardForFuture(boolean rememberCard, CreateCardDetailRequest createCardDetailRequest) throws BusinessException {
		if(rememberCard == true) {
			cardDetailService.add(createCardDetailRequest);
		}
	}
	
	private void saveOrderedAdditions(List<Integer> serviceIds, int rentalId) throws BusinessException {
		
		this.orderedAdditionalServiceService.add(serviceIds, rentalId);
	}
	
	private Invoice saveInvoice(Rental rental) throws BusinessException {
		
		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		createInvoiceRequest.setInvoiceNumber(RandomString.make(8));
		createInvoiceRequest.setCustomerId(rental.getCustomer().getCustomerId());
		createInvoiceRequest.setRentalId(rental.getRentalId());
		
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	private void savePayment(Payment payment, Rental rental, Invoice invoice) {
		
		payment.setInvoice(invoice);
		payment.setCustomerId(rental.getCustomer().getCustomerId());
		payment.setRentalId(rental.getRentalId());
		payment.setPaymentDate(LocalDate.now());
		payment.setTotalPayment(invoice.getTotal());
		
		payment.setRentalId(0);
		
		this.paymentDao.save(payment);
	}
	
	private Rental getByRental(CreateExtraDelayPaymentRequest createExtraDelayPaymentRequest) throws BusinessException {
		ListRentalDto listRentalDto = this.rentalService.getById(createExtraDelayPaymentRequest.getRentalId()).getData();
		Rental rental =  this.modelMapperService.forRequest().map(listRentalDto, Rental.class);
		return rental;
	}
	
	@Transactional
	private void paymentProcessing(boolean rememberCard, CreatePaymentRequest createPaymentRequest) throws BusinessException {
		
		Rental rental = this.rentalService.add(createPaymentRequest.getCreateRentalRequest());
		saveOrderedAdditions(createPaymentRequest.getAdditionalServiceIds(),rental.getRentalId());
		saveCardForFuture(rememberCard, createPaymentRequest.getCreateCardDetailRequest());
		Invoice invoice = saveInvoice(rental);
		
		Payment payment = this.modelMapperService.forRequest()
				.map(createPaymentRequest, Payment.class);
		savePayment(payment, rental, invoice);		
	}
	
	@Transactional
	private void extraDelayPaymentProcessing(CreateExtraDelayPaymentRequest createExtraDelayPaymentRequest) throws BusinessException {
	
		Rental rental = getByRental(createExtraDelayPaymentRequest);
		Invoice invoice = saveInvoice(rental);
		
		Payment payment = this.modelMapperService
				.forRequest().map(createExtraDelayPaymentRequest, Payment.class);
		
		savePayment(payment, rental, invoice);		
	}

	
}
