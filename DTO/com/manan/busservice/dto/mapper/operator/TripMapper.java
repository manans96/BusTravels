package com.manan.busservice.dto.mapper.operator;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.operations.BookingMapper;
import com.manan.busservice.dto.mapper.operations.TripDetailsMapper;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.model.operator.TripEntity;

public class TripMapper {
	
	public static Trip toTrip(TripEntity trip) {
		
		return new Trip()
				.setArriveCode(trip.getArriveStopCode())
				.setCode(trip.getCode())
				.setDepartCode(trip.getDepartStopCode())
				.setHaltStop1(trip.getHaltStop1())
				.setHaltStop2(trip.getHaltStop2())
				.setHaltTime(trip.getHaltTime())
				.setJourneyTime(trip.getJourneyTime())
				.setLastUpdate(trip.getLastUpdate())
				.setVisible(trip.isVisible())
				.setBooking(BookingMapper.toBooking(trip.getBooking()))
				.setTripDetails(TripDetailsMapper.toTripDetails(trip.getTripDetails()))
				.setBusOperator(BusOperatorMapper.toBusOperator(trip.getOperator()));
		
	}
	
	public static List<Trip> toTrip(List<TripEntity> tripList) {
		
		List<Trip> trips = new ArrayList<>();
		for(TripEntity trip : tripList) {
			trips.add(toTrip(trip));
		}
		return trips;
		
	}

}
