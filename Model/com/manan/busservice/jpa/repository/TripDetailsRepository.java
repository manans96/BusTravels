/**
 * 
 */
package com.manan.busservice.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.TripDetailsEntity;
import com.manan.busservice.model.operator.TripEntity;

/**
 * @author Manan Sanghvi
 *
 */
@Deprecated
public interface TripDetailsRepository extends JpaRepository<TripDetailsEntity, Integer> {
	
	Optional<TripDetailsEntity> findByTripDetailCode(String code);
	
	List<TripDetailsEntity> findByTripCode(TripEntity tripEntity);

}
