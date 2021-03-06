package com.RentACar.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.Invoice;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer>{

	Invoice getByInvoiceId(int invoiceId);
	
	List<Invoice> findByCreateDateBetween(LocalDate rentDate, LocalDate returnDate);
	List<Invoice> getByCustomer_CustomerId(int customerId);
	
}
