package com.manan.busservice.dto.mapper.operations;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.operator.BusMapper;
import com.manan.busservice.dto.mapper.operator.TripMapper;
import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.model.operations.TripDetailsEntity;

public class TripDetailsMapper {
	
	public static TripDetails toTripDetails(TripDetailsEntity tripDetails) {
		
		return new TripDetails()
				.setTripDetailCode(tripDetails.getTripDetailCode())
				.setAvailableSeats(tripDetails.getAvailableSeats())
				.setCost(tripDetails.getCost())
				.setDepartureTime(tripDetails.getDepartureTime())
				.setLastUpdate(tripDetails.getLastUpdate())
				.setBus(BusMapper.toBusFromTripDetails(tripDetails.getBus()))
				.setTripCode(TripMapper.toTripFromTripDetails(tripDetails.getTripCode()))
				.setActive(tripDetails.isActive());
		
	}
	
	public static List<TripDetails> toTripDetails(List<TripDetailsEntity> tripDetailsList) {
		
		List<TripDetails> tripDetails = new ArrayList<>();
		for(TripDetailsEntity tripDetail : tripDetailsList) {
			tripDetails.add(toTripDetails(tripDetail));
		}
		return tripDetails;
		
	}

}
