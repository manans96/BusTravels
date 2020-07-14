package com.manan.busservice.dto.mapper.operator;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.operations.TripMapper;
import com.manan.busservice.dto.mapper.user.UserMapper;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.model.operator.BusOperatorEntity;

public class BusOperatorMapper {
	
	public static BusOperator toBusOperator(BusOperatorEntity busOperator) {
		
		return new BusOperator()
				.setBus(BusMapper.toBus(busOperator.getBus()))
				.setLastUpdate(busOperator.getLastUpdate())
				.setOperatorCode(busOperator.getOperatorCode())
				.setOperatorDetails(busOperator.getOperatorDetails())
				.setOperatorName(busOperator.getOperatorName())
				.setOperator(UserMapper.toUser(busOperator.getOperator()))
				.setTrip(TripMapper.toTrip(busOperator.getTrip()));
		
	}
	
	public static List<BusOperator> toBusOperator(List<BusOperatorEntity> busOperator) {
		
		List<BusOperator> busOperators = new ArrayList<>();
		for(BusOperatorEntity busOp : busOperator) {
			busOperators.add(toBusOperator(busOp));
		}
		return busOperators;
		
	}

}
