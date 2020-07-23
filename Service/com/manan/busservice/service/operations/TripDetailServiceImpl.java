/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operations.TripDetailsMapper;
import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.TripDetailsEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class TripDetailServiceImpl implements TripDetailService {
	
	private Repositories.Container repos;
	
	@Autowired
	public TripDetailServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	private Optional<TripDetailsEntity> optional;
	
	private void findByTripDetailsCode(TripDetails tripDetails) {
		optional = repos.tripDetailsRepository.findByTripDetailCode(tripDetails.getTripDetailCode());
	}

	@Override
	public TripDetails addNewJourney(TripDetails tripDetails) {
		
		findByTripDetailsCode(tripDetails);
		if(optional.isEmpty()) {
			return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.save(new TripDetailsEntity()
					.setTripDetailCode(tripDetails.getTripDetailCode())
					.setDepartureTime(tripDetails.getDepartureTime())
					.setActive(true)
					.setAvailableSeats(tripDetails.getAvailableSeats())
					.setCost(tripDetails.getCost())
					.setLastUpdate(DateUtils.today())
					.setBus(repos.busRepository.findByBusCode(tripDetails.getBus().getBusCode())
							.get())
					.setTripCode(repos.tripRepository.findByCode(tripDetails.getTripCode().getCode())
							.get())
					));
		} else {
			return new TripDetails();
		}
	}

	@Override
	public TripDetails editJourney(TripDetails tripDetails) {

		findByTripDetailsCode(tripDetails);
		if(optional.isPresent()) {
			TripDetailsEntity tripDetailsEntity = optional.get();
			return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.save(tripDetailsEntity
					.setAvailableSeats(tripDetails.getAvailableSeats())
					.setCost(tripDetails.getCost())
					.setDepartureTime(tripDetails.getDepartureTime())
					.setLastUpdate(tripDetails.getLastUpdate())
					.setBus(repos.busRepository.findByBusCode(tripDetails.getBus().getBusCode())
							.get())
					));
		} else {
			return new TripDetails();
		}
	}

	@Override
	public TripDetails disableTrip(TripDetails tripDetails) {

		findByTripDetailsCode(tripDetails);
		if(optional.isPresent()) {
			TripDetailsEntity tripDetailsEntity = optional.get();
			return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.save(tripDetailsEntity
					.setActive(false)
					.setLastUpdate(DateUtils.today())));
		} else {
			return new TripDetails();
		}
	}

	@Override
	public TripDetails enableTrip(TripDetails tripDetails) {

		findByTripDetailsCode(tripDetails);
		if(optional.isPresent()) {
			TripDetailsEntity tripDetailsEntity = optional.get();
			return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.save(tripDetailsEntity
					.setActive(true)
					.setLastUpdate(DateUtils.today())));
		} else {
			return new TripDetails();
		}
	}

	@Override
	public TripDetails viewTrip(TripDetails tripDetails) {

		findByTripDetailsCode(tripDetails);
		return TripDetailsMapper.toTripDetails(optional.get());
	}

	@Override
	public List<TripDetails> viewAllTrips() {

		return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.findAll());
	}

	@Override
	public List<TripDetails> viewTripDetailsByTrip(Trip trip) {

		return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.findByTripCode(repos.tripRepository.findByCode(trip.getCode())
				.get()));
	}

}
