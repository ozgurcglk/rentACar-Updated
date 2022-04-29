package com.RentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RentACar.entities.concretes.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	User getByUserId(int userId);
}
