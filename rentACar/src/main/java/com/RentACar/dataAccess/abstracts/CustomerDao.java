package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	Customer getByCustomerId(int customerId);
}
