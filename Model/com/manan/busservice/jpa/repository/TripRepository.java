package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.TripEntity;

public interface TripRepository extends JpaRepository<TripEntity, Integer> {

}
