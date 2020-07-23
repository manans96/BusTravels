package com.manan.busservice.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.StopEntity;

@Deprecated
public interface StopRepository extends JpaRepository<StopEntity, Integer> {
	
	Optional<StopEntity> findByStopCode(String code);

}
