/**
 * 
 */
package com.manan.busservice.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.TicketEntity;
import com.manan.busservice.model.operations.TripDetailsEntity;
import com.manan.busservice.model.user.UserEntity;

/**
 * @author Manan Sanghvi
 *
 */
@Deprecated
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
	
	Optional<TicketEntity> findByTicketNumber(String ticketNumber);
	
	List<TicketEntity> findByPassenger(UserEntity userEntity);
	
	List<TicketEntity> findByTripDetails(TripDetailsEntity tripDetailsEntity);

}
