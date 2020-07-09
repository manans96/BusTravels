package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.Stop;

public interface StopRepository extends JpaRepository<Stop, Integer> {

}
