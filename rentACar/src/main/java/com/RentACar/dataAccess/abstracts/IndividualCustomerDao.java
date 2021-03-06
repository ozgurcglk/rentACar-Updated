package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.IndividualCustomer;

@Repository
public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer> {
	
	IndividualCustomer getByIndiCustomerId(int indiCustomerId);
	IndividualCustomer findByEmail(String email);
}
