package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.CorporateCustomer;

@Repository
public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> {
	
	CorporateCustomer getByCorpCustomerId(int corpCustomerId);
	CorporateCustomer findByEmail(String email);
}
