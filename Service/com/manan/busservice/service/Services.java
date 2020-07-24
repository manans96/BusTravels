/**
 * 
 */
package com.manan.busservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.manan.busservice.dto.model.operations.Booking;
import com.manan.busservice.dto.model.operations.Stop;
import com.manan.busservice.dto.model.operations.Ticket;
import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.dto.model.user.UserAuth;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class Services {
	
	@Service
	public interface UserService {
		
		User signup(User user, UserAuth userAuth);
		
		User login(String userName, String password);
		
		User updateProfile(User user);
		
		User updatePassword(String userName, String oldPassword, String newPassword);
		
		User changeRole(String userName, String role);
		
		User findUser(String userName);

		@Deprecated
		User findUser(User user);
		
		List<User> findAllUsers();

	}

	@Service
	public interface BusOperatorService {
		
		BusOperator addNewOperator(BusOperator busOperator);
		
		BusOperator updateBusOperatorDetails(BusOperator busOperator);
		
		void deleteBusOperator(String operatorCode);
		
		BusOperator viewBusOperator(String operatorCode);
		
		List<BusOperator> viewAllBusOperators();

	}

	@Service
	public interface BusService {
		
		Bus addBus(Bus bus);
		
		Bus editBus(Bus bus);
		
		Bus viewBus(String busCode);
		
		List<Bus> viewAllBus();
		
		List<Bus> viewAllBusByOperator(String operatorCode);
		
		Bus disableBus(String busCode);
		
		Bus enableBus(String busCode);

	}

	@Service
	public interface TripService {
		
		Trip addTrip(Trip trip);
		
		Trip editTrip(Trip trip);
		
		Trip viewTrip(String code);
		
		List<Trip> viewAllTrips();
		
		List<Trip> viewAllTripsByOperator(String operatorCode);
		
		Trip disableTrip(String code);
		
		Trip enableTrip(String code);

	}

	@Service
	public interface TripDetailService {
		
		TripDetails addNewJourney(TripDetails tripDetails);
		
		TripDetails editJourney(TripDetails tripDetails);
		
		TripDetails disableTrip(TripDetails tripDetails);
		
		TripDetails enableTrip(TripDetails tripDetails);
		
		TripDetails viewTrip(TripDetails tripDetails);
		
		List<TripDetails> viewTripDetailsByTrip(Trip trip);
		
		List<TripDetails> viewAllTrips();

	}

	@Service
	public interface TicketService {
		
		Ticket newTicket(Ticket ticket);
		
		Ticket cancelTicket(Ticket ticket);
		
		Ticket viewTicket(Ticket ticket);
		
		List<Ticket> viewAllTicketByUser(User user);
		
		List<Ticket> viewAllTicketByTripDetails(TripDetails tripDetails);
		
		List<Ticket> viewAllTickets();

	}

	@Service
	public interface BookingService {
		
		Booking newBooking(Booking booking);
		
		Booking editBooking(Booking booking);
		
		void cancelBooking(Booking booking);
		
		Booking viewBooking(Booking booking);
		
		List<Booking> viewAllBookingByUser(User user);
		
		List<Booking> viewBookingsByTrip(Trip trip);
		
		List<Booking> viewAllBookings();

	}

	@Service
	public interface StopService {
		
		Stop addStop(Stop stop);
		
		Stop editStop(Stop stop);
		
		Stop findStop(Stop stop);
		
		List<Stop> listAllStops();

	}
	
	@Component
	public class Container {
		
		public UserService userService;
		public BusOperatorService busOperatorService;
		public BusService busService;
		public TripService tripService;
		public TripDetailService tripDetailService;
		public TicketService ticketService;
		public BookingService bookingService;
		public StopService stopService;
		
		@Autowired
		public Container(UserService userService,
				BusOperatorService busOperatorService,
				BusService busService,
				TripService tripService,
				TripDetailService tripDetailService,
				TicketService ticketService,
				BookingService bookingService,
				StopService stopService) {
			this.userService = userService;
			this.busOperatorService = busOperatorService;
			this.busService = busService;
			this.tripService = tripService;
			this.tripDetailService = tripDetailService;
			this.ticketService = ticketService;
			this.bookingService = bookingService;
			this.stopService = stopService;
		}
	}

}
