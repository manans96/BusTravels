package com.manan.busservice.dto.mapper.operations;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.operator.BusMapper;
import com.manan.busservice.dto.mapper.user.UserMapper;
import com.manan.busservice.dto.model.operations.Booking;
import com.manan.busservice.model.operations.BookingEntity;

public class BookingMapper {
	
	public static Booking toBooking(BookingEntity booking) {
		
		return new Booking()
				.setDepartureTime(booking.getDepartureTime())
				.setLastUpdate(booking.getLastUpdate())
				.setTotalCost(booking.getTotalCost())
				.setCancelled(booking.isCancelled())
				.setPassenger(UserMapper.toUser(booking.getPassenger()))
				.setBus(BusMapper.toBus(booking.getBus()))
				.setTripCode(TripMapper.toTrip(booking.getTripCode()));
		
	}
	
	public static List<Booking> toBooking(List<BookingEntity> bookingList) {
		
		List<Booking> bookings = new ArrayList<>();
		for(BookingEntity booking : bookingList) {
			bookings.add(toBooking(booking));
		}
		return bookings;
		
	}

}
