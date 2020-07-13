/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;

import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.user.User;

/**
 * @author Manan Sanghvi
 *
 */
public interface BusOperatorService {
	
	BusOperator addNewOperator(BusOperator busOperator, User user);
	
	BusOperator updateBusOperatorDetails(BusOperator busOperator);
	
	void deleteBusOperator(BusOperator busOperator);
	
	BusOperator viewBusOperator(BusOperator busOperator);
	
	List<BusOperator> viewAllBusOperators();

}
