/**
 * 
 */
package com.manan.busservice.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operator.BusOperatorEntity;
import com.manan.busservice.model.operator.TripEntity;

/**
 * @author Manan Sanghvi
 *
 */
public interface TripRepository extends JpaRepository<TripEntity, Integer> {
	
	Optional<TripEntity> findByCode(String code);
	
	List<TripEntity> findByOperator(BusOperatorEntity busOperatorEntity);

}
