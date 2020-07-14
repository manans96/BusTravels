/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;

import com.manan.busservice.dto.model.operator.BusOperator;

/**
 * @author Manan Sanghvi
 *
 */
public interface BusOperatorService {
	
	BusOperator addNewOperator(BusOperator busOperator);
	
	BusOperator updateBusOperatorDetails(BusOperator busOperator);
	
	void deleteBusOperator(BusOperator busOperator);
	
	BusOperator viewBusOperator(String operatorCode);
	
	List<BusOperator> viewAllBusOperators();

}
