package com.manan.busservice.dto.mapper.operator;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.user.UserMapper;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.model.operator.BusOperatorEntity;

public class BusOperatorMapper {
	
	public static BusOperator toBusOperator(BusOperatorEntity busOperator) {
		
		BusOperator operator = new BusOperator();
		
		if(busOperator.getBus() != null) {
			operator.setBus(BusMapper.toBusFromOperator(busOperator.getBus()));
		}
		if(busOperator.getTrip() != null) {
			operator.setTrip(TripMapper.toTripFromOperator(busOperator.getTrip()));
		}
		
		return operator
				.setLastUpdate(busOperator.getLastUpdate())
				.setOperatorCode(busOperator.getOperatorCode())
				.setOperatorDetails(busOperator.getOperatorDetails())
				.setOperatorName(busOperator.getOperatorName())
				.setOperator(UserMapper.toUser(busOperator.getOperator()));
		
	}
	
	public static List<BusOperator> toBusOperator(List<BusOperatorEntity> busOperator) {
		
		List<BusOperator> busOperators = new ArrayList<>();
		for(BusOperatorEntity busOp : busOperator) {
			busOperators.add(toBusOperator(busOp));
		}
		return busOperators;
		
	}

}
