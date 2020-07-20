/**
 * 
 */
package com.manan.busservice.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.BookingEntity;
import com.manan.busservice.model.user.UserEntity;

/**
 * @author Manan Sanghvi
 *
 */
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
	
	List<BookingEntity> findByPassenger(UserEntity userEntity);
	
	Optional<BookingEntity> findByBookingCode(String code);

}
