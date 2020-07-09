package com.manan.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operator.BusOperator;

public interface BusOperatorRepository extends JpaRepository<BusOperator, Integer> {

}
