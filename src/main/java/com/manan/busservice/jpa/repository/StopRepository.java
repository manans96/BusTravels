package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.StopEntity;

public interface StopRepository extends JpaRepository<StopEntity, Integer> {

}
