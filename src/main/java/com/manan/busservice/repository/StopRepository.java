package com.manan.busservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.Stop;

public interface StopRepository extends JpaRepository<Stop, Integer> {

}
