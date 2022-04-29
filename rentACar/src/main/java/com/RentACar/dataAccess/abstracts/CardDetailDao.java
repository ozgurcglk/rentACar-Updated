package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.CardDetail;

@Repository
public interface CardDetailDao extends JpaRepository<CardDetail, Integer> {
	
	CardDetail getByCardDetailId(int cardDetailId);
	CardDetail    existsByCardNo(String cardNo);
}
