/**
 * 
 */
package com.manan.busservice.service.deprecated;

import java.util.List;

import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.operator.Trip;

/**
 * @author Manan Sanghvi
 *
 */
@Deprecated
public interface TripDetailService {
	
	TripDetails addNewJourney(TripDetails tripDetails);
	
	TripDetails editJourney(TripDetails tripDetails);
	
	TripDetails disableTrip(TripDetails tripDetails);
	
	TripDetails enableTrip(TripDetails tripDetails);
	
	TripDetails viewTrip(TripDetails tripDetails);
	
	List<TripDetails> viewTripDetailsByTrip(Trip trip);
	
	List<TripDetails> viewAllTrips();

}
