/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;

import com.manan.busservice.dto.model.operations.Booking;
import com.manan.busservice.dto.model.operations.Trip;
import com.manan.busservice.dto.model.user.User;

/**
 * @author Manan Sanghvi
 *
 */
public interface BookingService {
	
	Booking newBooking(Trip trip, Booking booking, User user);
	
	Booking editBooking(Booking booking);
	
	void cancelBooking(Booking booking);
	
	Booking viewBooking(Booking booking);
	
	List<Booking> viewAllBookingByUser(User user);
	
	List<Booking> viewAllBookings();

}
