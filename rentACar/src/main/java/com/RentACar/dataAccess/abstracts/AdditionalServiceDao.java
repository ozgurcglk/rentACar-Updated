package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.AdditionalService;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer>{
	AdditionalService getByServiceId(int serviceId);
}
