/**
 * 
 */
package com.manan.busservice.service.deprecated;

import java.util.List;

import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.operator.Trip;

/**
 * @author Manan Sanghvi
 *
 */
@Deprecated
public interface TripService {
	
	Trip addTrip(Trip trip);
	
	Trip editTrip(Trip trip);
	
	Trip viewTrip(Trip trip);
	
	List<Trip> viewAllTrips();
	
	List<Trip> viewAllTripsByOperator(BusOperator busOperator);
	
	Trip disableTrip(Trip trip);
	
	Trip enableTrip(Trip trip);

}
