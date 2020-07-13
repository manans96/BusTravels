/**
 * 
 */
package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.TripDetailsEntity;

/**
 * @author Manan Sanghvi
 *
 */
public interface TripDetailsRepository extends JpaRepository<TripDetailsEntity, Integer> {

}
