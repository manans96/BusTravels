package com.manan.busservice.dto.mapper.operator;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.operations.BookingMapper;
import com.manan.busservice.dto.mapper.operations.TripDetailsMapper;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.model.operator.TripEntity;

public class TripMapper {
	
	public static Trip toTrip(TripEntity trip) {
		
		Trip tripMap = new Trip();
		
		if(trip.getBooking() != null) {
			tripMap.setBooking(BookingMapper.toBooking(trip.getBooking()));
		}
		if(trip.getTripDetails() != null) {
			tripMap.setTripDetails(TripDetailsMapper.toTripDetails(trip.getTripDetails()));
		}
		
		return tripMap
				.setArriveCode(trip.getArriveStopCode())
				.setCode(trip.getCode())
				.setDepartCode(trip.getDepartStopCode())
				.setHaltStop1(trip.getHaltStop1())
				.setHaltStop2(trip.getHaltStop2())
				.setHaltTime(trip.getHaltTime())
				.setJourneyTime(trip.getJourneyTime())
				.setLastUpdate(trip.getLastUpdate())
				.setVisible(trip.isVisible())
				.setBusOperator(BusOperatorMapper.toBusOperator(trip.getOperator()));
		
	}
	
	public static List<Trip> toTrip(List<TripEntity> tripList) {
		
		List<Trip> trips = new ArrayList<>();
		for(TripEntity trip : tripList) {
			trips.add(toTrip(trip));
		}
		return trips;
		
	}

	//Used to prevent error when called from BusOperatorMapper
	public static List<Trip> toTripFromOperator(List<TripEntity> tripList) {
		
		List<Trip> trips = new ArrayList<>();
		Trip tripMap = new Trip();
		
		for(TripEntity trip : tripList) {
			
			if(trip.getBooking() != null) {
				tripMap.setBooking(BookingMapper.toBooking(trip.getBooking()));
			}
			if(trip.getTripDetails() != null) {
				tripMap.setTripDetails(TripDetailsMapper.toTripDetails(trip.getTripDetails()));
			}
			
			tripMap
			.setArriveCode(trip.getArriveStopCode())
			.setCode(trip.getCode())
			.setDepartCode(trip.getDepartStopCode())
			.setHaltStop1(trip.getHaltStop1())
			.setHaltStop2(trip.getHaltStop2())
			.setHaltTime(trip.getHaltTime())
			.setJourneyTime(trip.getJourneyTime())
			.setLastUpdate(trip.getLastUpdate())
			.setVisible(trip.isVisible());
			
			trips.add(tripMap);
		}
		return trips;
	}

	public static Trip toTripFromBooking(TripEntity trip) {
		
		return new Trip()
				.setArriveCode(trip.getArriveStopCode())
				.setCode(trip.getCode())
				.setDepartCode(trip.getDepartStopCode())
				.setHaltStop1(trip.getHaltStop1())
				.setHaltStop2(trip.getHaltStop2())
				.setHaltTime(trip.getHaltTime())
				.setJourneyTime(trip.getJourneyTime())
				.setLastUpdate(trip.getLastUpdate())
				.setVisible(trip.isVisible());
		
	}
	
	public static Trip toTripFromTripDetails(TripEntity trip) {
		
		return new Trip()
				.setArriveCode(trip.getArriveStopCode())
				.setCode(trip.getCode())
				.setDepartCode(trip.getDepartStopCode())
				.setHaltStop1(trip.getHaltStop1())
				.setHaltStop2(trip.getHaltStop2())
				.setHaltTime(trip.getHaltTime())
				.setJourneyTime(trip.getJourneyTime())
				.setLastUpdate(trip.getLastUpdate())
				.setVisible(trip.isVisible());
		
	}
}
