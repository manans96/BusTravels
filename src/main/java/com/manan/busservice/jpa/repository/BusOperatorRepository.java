package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operator.BusOperatorDB;

public interface BusOperatorRepository extends JpaRepository<BusOperatorDB, Integer> {

}
