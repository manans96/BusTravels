/**
 * 
 */
package com.manan.busservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.BookingEntity;

/**
 * @author Manan Sanghvi
 *
 */
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

}
