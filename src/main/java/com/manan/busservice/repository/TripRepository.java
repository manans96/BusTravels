package com.manan.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {

}
