package com.manan.busservice.dto.mapper.operator;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.operations.BookingMapper;
import com.manan.busservice.dto.mapper.operations.TripDetailsMapper;
import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.model.operator.BusEntity;

public class BusMapper {
	
	public static Bus toBus(BusEntity bus) {
		
		return new Bus()
				.setBusCode(bus.getBusCode())
				.setBusModel(bus.getBusModel())
				.setHaltCost(bus.getHaltCost())
				.setLastUpdate(bus.getLastUpdate())
				.setAvailable(bus.isAvailable())
				.setCapacity(bus.getCapacity())
				.setRunCost(bus.getRunCost())
				.setTripDetails(TripDetailsMapper.toTripDetails(bus.getTripDetails()))
				.setBooking(BookingMapper.toBooking(bus.getBooking()))
				.setOperator(BusOperatorMapper.toBusOperator(bus.getOperator()));
		
	}
	
	public static List<Bus> toBus(List<BusEntity> busList) {
		
		List<Bus> buses = new ArrayList<>();
		for(BusEntity bus : busList) {
			buses.add(toBus(bus));
		}
		return buses;
		
	}

}
