package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer>{
	boolean existsByCityId(int cityId);
}
