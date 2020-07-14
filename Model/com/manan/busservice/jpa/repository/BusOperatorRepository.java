package com.manan.busservice.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operator.BusOperatorEntity;

public interface BusOperatorRepository extends JpaRepository<BusOperatorEntity, Integer> {
	
	Optional<BusOperatorEntity> findByOperatorCode(String code);

}
