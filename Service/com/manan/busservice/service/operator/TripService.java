/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;

import com.manan.busservice.dto.model.operations.Trip;
import com.manan.busservice.dto.model.operator.BusOperator;

/**
 * @author Manan Sanghvi
 *
 */
public interface TripService {
	
	Trip addTrip(BusOperator busOperator, Trip trip);
	
	Trip editTrip(Trip trip);
	
	Trip viewTrip(Trip trip);
	
	List<Trip> viewAllTrips();
	
	List<Trip> viewAllTripsByOperator(BusOperator busOperator);
	
	void disableTrip(Trip trip);
	
	Trip enableTrip(Trip trip);

}
