/**
 * 
 */
package com.manan.busservice.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.manan.busservice.model.operations.BookingEntity;
import com.manan.busservice.model.operations.StopEntity;
import com.manan.busservice.model.operations.TicketEntity;
import com.manan.busservice.model.operations.TripDetailsEntity;
import com.manan.busservice.model.operator.BusEntity;
import com.manan.busservice.model.operator.BusOperatorEntity;
import com.manan.busservice.model.operator.TripEntity;
import com.manan.busservice.model.user.UserEntity;

/**
 * @author Manan Sanghvi
 *
 */
public class Repositories {
	
	public interface UserRepository extends JpaRepository<UserEntity, Integer> {
		
		Optional<UserEntity> findByUserName(String userName);

	}
	
	public interface BusOperatorRepository extends JpaRepository<BusOperatorEntity, Integer> {
		
		Optional<BusOperatorEntity> findByOperatorCode(String code);

	}
	
	public interface BusRepository extends JpaRepository<BusEntity, Integer> {
		
		Optional<BusEntity> findByBusCode(String code);
		
		List<BusEntity> findByOperator(BusOperatorEntity busOperatorEntity);

	}
	
	public interface TripRepository extends JpaRepository<TripEntity, Integer> {
		
		Optional<TripEntity> findByCode(String code);
		
		List<TripEntity> findByOperator(BusOperatorEntity busOperatorEntity);

	}
	
	public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
		
		List<BookingEntity> findByPassenger(UserEntity userEntity);
		
		Optional<BookingEntity> findByBookingCode(String code);

		List<BookingEntity> findByTripCode(TripEntity tripEntity);

	}
	
	public interface TripDetailsRepository extends JpaRepository<TripDetailsEntity, Integer> {
		
		Optional<TripDetailsEntity> findByTripDetailCode(String code);
		
		List<TripDetailsEntity> findByTripCode(TripEntity tripEntity);

	}
	
	public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
		
		Optional<TicketEntity> findByTicketNumber(String ticketNumber);
		
		List<TicketEntity> findByPassenger(UserEntity userEntity);
		
		List<TicketEntity> findByTripDetails(TripDetailsEntity tripDetailsEntity);

	}
	
	public interface StopRepository extends JpaRepository<StopEntity, Integer> {
		
		Optional<StopEntity> findByStopCode(String code);

	}
	
	
	public class Container {
		
		public UserRepository userRepository;
		public BusOperatorRepository busOperatorRepository;
		public BusRepository busRepository;
		public TripRepository tripRepository;
		public BookingRepository bookingRepository;
		public TripDetailsRepository tripDetailsRepository;
		public TicketRepository ticketRepository;
		public StopRepository stopRepository;
		
		@Autowired
		public Container(UserRepository userRepository,
				BusOperatorRepository busOperatorRepository,
				BusRepository busRepository,
				TripRepository tripRepository,
				BookingRepository bookingRepository,
				TripDetailsRepository tripDetailsRepository,
				TicketRepository ticketRepository,
				StopRepository stopRepository) {
			this.userRepository = userRepository;
			this.busOperatorRepository = busOperatorRepository;
			this.busRepository = busRepository;
			this.tripRepository = tripRepository;
			this.bookingRepository = bookingRepository;
			this.tripDetailsRepository = tripDetailsRepository;
			this.ticketRepository = ticketRepository;
			this.stopRepository = stopRepository;
		}
		
	}
}
