package com.manan.busservice.dto.mapper.operations;

import com.manan.busservice.dto.model.operations.Stop;
import com.manan.busservice.model.operations.StopEntity;

public class StopMapper {
	
	public static Stop toStop(StopEntity stop) {
		
		return new Stop()
				.setStopCode(stop.getStopCode())
				.setStopName(stop.getStopName())
				.setStopType(stop.getStopType());
		
	}

}
