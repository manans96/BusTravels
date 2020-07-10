package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operator.BusEntity;

public interface BusRepository extends JpaRepository<BusEntity, Integer> {

}
