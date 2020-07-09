package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.TripDB;

public interface TripRepository extends JpaRepository<TripDB, Integer> {

}
