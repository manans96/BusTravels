package com.manan.busservice.dto.mapper.operator;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.operations.BookingMapper;
import com.manan.busservice.dto.mapper.operations.TripDetailsMapper;
import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.model.operator.BusEntity;

public class BusMapper {
	
	public static Bus toBus(BusEntity bus) {
		
		Bus busMap = new Bus();
		
		if(bus.getBooking() != null) {
			busMap.setBooking(BookingMapper.toBooking(bus.getBooking()));
		}
		if(bus.getTripDetails() != null) {
			busMap.setTripDetails(TripDetailsMapper.toTripDetails(bus.getTripDetails()));
		}
		
		return busMap
				.setBusCode(bus.getBusCode())
				.setBusModel(bus.getBusModel())
				.setHaltCost(bus.getHaltCost())
				.setLastUpdate(bus.getLastUpdate())
				.setAvailable(bus.isAvailable())
				.setCapacity(bus.getCapacity())
				.setRunCost(bus.getRunCost())
				.setOperator(BusOperatorMapper.toBusOperator(bus.getOperator()));
	}
	
	public static List<Bus> toBus(List<BusEntity> busList) {
		
		List<Bus> buses = new ArrayList<>();
		for(BusEntity bus : busList) {
			buses.add(toBus(bus));
		}
		return buses;
	}
	
	//Used to prevent error when called from BusOperatorMapper
	public static List<Bus> toBusFromOperator(List<BusEntity> busList) {
		
		List<Bus> buses = new ArrayList<>();
		Bus busMap = new Bus();
		for(BusEntity bus : busList) {
			
			if(bus.getBooking() != null) {
				busMap.setBooking(BookingMapper.toBooking(bus.getBooking()));
			}
			if(bus.getTripDetails() != null) {
				busMap.setTripDetails(TripDetailsMapper.toTripDetails(bus.getTripDetails()));
			}
			
			busMap
				.setBusCode(bus.getBusCode())
				.setBusModel(bus.getBusModel())
				.setHaltCost(bus.getHaltCost())
				.setLastUpdate(bus.getLastUpdate())
				.setAvailable(bus.isAvailable())
				.setCapacity(bus.getCapacity())
				.setRunCost(bus.getRunCost());
			
			buses.add(busMap);
		}
		return buses;
	}

	//Used to prevent error when called from BookingMapper
	public static Bus toBusFromBooking(BusEntity bus) {
		
		Bus busMap = new Bus();
		
		return busMap
				.setBusCode(bus.getBusCode())
				.setBusModel(bus.getBusModel())
				.setHaltCost(bus.getHaltCost())
				.setLastUpdate(bus.getLastUpdate())
				.setAvailable(bus.isAvailable())
				.setCapacity(bus.getCapacity())
				.setRunCost(bus.getRunCost());
	}
	
	//Used to prevent error when called from TripDetailsMapper
	public static Bus toBusFromTripDetails(BusEntity bus) {
		
		Bus busMap = new Bus();
		
		return busMap
				.setBusCode(bus.getBusCode())
				.setBusModel(bus.getBusModel())
				.setHaltCost(bus.getHaltCost())
				.setLastUpdate(bus.getLastUpdate())
				.setAvailable(bus.isAvailable())
				.setCapacity(bus.getCapacity())
				.setRunCost(bus.getRunCost());
	}
}
