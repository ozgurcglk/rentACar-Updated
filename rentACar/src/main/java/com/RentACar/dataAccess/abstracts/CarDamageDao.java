package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.CarDamage;

@Repository
public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {

}
