/**
 * 
 */
package com.manan.busservice.service.deprecated;

import java.util.List;

import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.dto.model.operator.BusOperator;

/**
 * @author Manan Sanghvi
 *
 */

@Deprecated
public interface BusService {
	
	Bus addBus(Bus bus);
	
	Bus editBus(Bus bus);
	
	Bus viewBus(Bus bus);
	
	List<Bus> viewAllBus();
	
	List<Bus> viewAllBusByOperator(BusOperator busOperator);
	
	Bus disableBus(Bus bus);
	
	Bus enableBus(Bus bus);

}
