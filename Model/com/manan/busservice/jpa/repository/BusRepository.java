/**
 * 
 */
package com.manan.busservice.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operator.BusEntity;

/**
 * @author Manan Sanghvi
 *
 */
public interface BusRepository extends JpaRepository<BusEntity, Integer> {
	
	Optional<BusEntity> findByBusCode(String code);
	
	List<BusEntity> findByOperator(int operatorId);

}
