package com.manan.busservice.dto.mapper.operator;

import com.manan.busservice.dto.mapper.operations.TripMapper;
import com.manan.busservice.dto.mapper.user.UserMapper;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.model.operator.BusOperatorEntity;

public class BusOperatorMapper {
	
	public static BusOperator toBusOperator(BusOperatorEntity busOperator) {
		
		return new BusOperator()
				.setBus(BusMapper.toBus(busOperator.getBus()))
				.setLastUpdate(busOperator.getLastUpdate())
				.setOperatorCode(busOperator.getOperatoreCode())
				.setOperatorDetails(busOperator.getOperatorDetails())
				.setOperatorName(UserMapper.toUser(busOperator.getOperatorName()))
				.setTrip(TripMapper.toTrip(busOperator.getTrip()));
		
	}

}
