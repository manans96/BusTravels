/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;

import com.manan.busservice.dto.model.operations.Stop;

/**
 * @author Manan Sanghvi
 *
 */
public interface StopService {
	
	Stop addStop(Stop stop);
	
	Stop editStop(Stop stop);
	
	Stop findStop(Stop stop);
	
	List<Stop> listAllStops();

}
