package com.RentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.CardDetail;

@Repository
public interface CardDetailDao extends JpaRepository<CardDetail, Integer> {
	
	List<CardDetail> getByCustomer_CustomerId(int customerId);

}
