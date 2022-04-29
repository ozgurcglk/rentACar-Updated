package com.RentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.CarMaintenance;

@Repository
public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer>{
	CarMaintenance getByCarMaintenanceId(int carMaintenanceId);
	List<CarMaintenance> getByCar_CarId(int carId);
}
