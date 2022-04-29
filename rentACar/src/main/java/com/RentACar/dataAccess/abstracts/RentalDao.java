package com.RentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer>{
		Rental getByRentalId(int rentalId);
		
		List<Rental> getByCar_CarId(int carId);
		List<Rental> getByCustomer_CustomerId(int customerId);

}
