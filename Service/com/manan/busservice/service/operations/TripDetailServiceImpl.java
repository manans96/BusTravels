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
import com.manan.busservice.jpa.repository.BusRepository;
import com.manan.busservice.jpa.repository.TripDetailsRepository;
import com.manan.busservice.jpa.repository.TripRepository;
import com.manan.busservice.model.operations.TripDetailsEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class TripDetailServiceImpl implements TripDetailService {
	
	private TripDetailsRepository tripDetailsRepository;
	private TripRepository tripRepository;
	private BusRepository busRepository;
	
	@Autowired
	public TripDetailServiceImpl(TripDetailsRepository tripDetailsRepository, TripRepository tripRepository, BusRepository busRepository) {
		this.tripDetailsRepository = tripDetailsRepository;
		this.tripRepository = tripRepository;
		this.busRepository = busRepository;
	}
	
	private Optional<TripDetailsEntity> optional;
	
	private void findByTripDetailsCode(TripDetails tripDetails) {
		optional = tripDetailsRepository.findByTripDetailCode(tripDetails.getTripDetailCode());
	}

	@Override
	public TripDetails addNewJourney(TripDetails tripDetails) {
		
		findByTripDetailsCode(tripDetails);
		if(optional.isEmpty()) {
			return TripDetailsMapper.toTripDetails(tripDetailsRepository.save(new TripDetailsEntity()
					.setTripDetailCode(tripDetails.getTripDetailCode())
					.setDepartureTime(tripDetails.getDepartureTime())
					.setActive(true)
					.setAvailableSeats(tripDetails.getAvailableSeats())
					.setCost(tripDetails.getCost())
					.setLastUpdate(DateUtils.today())
					.setBus(busRepository.findByBusCode(tripDetails.getBus().getBusCode())
							.get())
					.setTripCode(tripRepository.findByCode(tripDetails.getTripCode().getCode())
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
			return TripDetailsMapper.toTripDetails(tripDetailsRepository.save(tripDetailsEntity
					.setAvailableSeats(tripDetails.getAvailableSeats())
					.setCost(tripDetails.getCost())
					.setDepartureTime(tripDetails.getDepartureTime())
					.setLastUpdate(tripDetails.getLastUpdate())
					.setBus(busRepository.findByBusCode(tripDetails.getBus().getBusCode())
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
			return TripDetailsMapper.toTripDetails(tripDetailsRepository.save(tripDetailsEntity
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
			return TripDetailsMapper.toTripDetails(tripDetailsRepository.save(tripDetailsEntity
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

		return TripDetailsMapper.toTripDetails(tripDetailsRepository.findAll());
	}

	@Override
	public List<TripDetails> viewTripDetailsByTrip(Trip trip) {

		return TripDetailsMapper.toTripDetails(tripDetailsRepository.findByTripCode(tripRepository.findByCode(trip.getCode())
				.get()));
	}

}
