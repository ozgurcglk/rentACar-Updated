package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
	Payment getByPaymentId(int paymentId);
}
