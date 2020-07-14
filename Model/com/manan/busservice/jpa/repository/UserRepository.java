package com.manan.busservice.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByUserName(String userName);

}
