package com.manan.busservice.dto.mapper.operations;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.model.operations.Stop;
import com.manan.busservice.model.operations.StopEntity;

public class StopMapper {
	
	public static Stop toStop(StopEntity stop) {
		
		return new Stop()
				.setStopCode(stop.getStopCode())
				.setStopName(stop.getStopName())
				.setStopType(stop.getStopType());
		
	}
	
	public static List<Stop> toStop(List<StopEntity> stopList) {
		
		List<Stop> stops = new ArrayList<>();
		for(StopEntity stop : stopList) {
			stops.add(toStop(stop));
		}
		return stops;
	}

}
