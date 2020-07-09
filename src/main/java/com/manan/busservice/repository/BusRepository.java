package com.manan.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operator.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {

}
