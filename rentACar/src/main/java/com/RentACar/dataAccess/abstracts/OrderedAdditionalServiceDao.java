package com.RentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.OrderedAdditionalService;

@Repository
public interface OrderedAdditionalServiceDao extends JpaRepository<OrderedAdditionalService, Integer> {
	OrderedAdditionalService getByOrderedAdditionalServiceId(int orderedAdditionalServiceId);
	List<OrderedAdditionalService> getByRental_RentalId(int Id);
	
}
