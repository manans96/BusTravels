/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;

import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.operator.Trip;

/**
 * @author Manan Sanghvi
 *
 */
public interface TripDetailService {
	
	TripDetails addNewJourney(Trip trip, TripDetails tripDetails);
	
	TripDetails editJourney(TripDetails tripDetails);
	
	TripDetails disableTrip(TripDetails tripDetails);
	
	TripDetails enableTrip(TripDetails tripDetails);
	
	TripDetails viewTrip(TripDetails tripDetails);
	
	List<TripDetails> viewAllTrips();

}
