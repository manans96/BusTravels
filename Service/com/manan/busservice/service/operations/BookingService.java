/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;

import com.manan.busservice.dto.model.operations.Booking;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.dto.model.user.User;

/**
 * @author Manan Sanghvi
 *
 */
@Deprecated
public interface BookingService {
	
	Booking newBooking(Booking booking);
	
	Booking editBooking(Booking booking);
	
	void cancelBooking(Booking booking);
	
	Booking viewBooking(Booking booking);
	
	List<Booking> viewAllBookingByUser(User user);
	
	List<Booking> viewBookingsByTrip(Trip trip);
	
	List<Booking> viewAllBookings();

}
