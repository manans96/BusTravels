/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;

import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.dto.model.operator.BusOperator;

/**
 * @author Manan Sanghvi
 *
 */
public interface BusService {
	
	Bus addBus(BusOperator busOperator, Bus bus);
	
	Bus editBus(Bus bus);
	
	Bus viewBus(Bus bus);
	
	List<Bus> viewAllBus();
	
	void disableBus(Bus bus);
	
	Bus enableBus(Bus bus);

}
