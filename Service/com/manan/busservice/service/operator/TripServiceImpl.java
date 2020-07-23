/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operator.TripMapper;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operator.TripEntity;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class TripServiceImpl implements Services.TripService {
	
	private Repositories.Container repos;
	
	@Autowired
	public TripServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	Optional<TripEntity> optional;
	
	private void findByCode(Trip trip) {
		optional = repos.tripRepository.findByCode(trip.getCode());
	}

	@Override
	public Trip addTrip(Trip trip) {

		findByCode(trip);
		if(optional.isEmpty()) {
			return TripMapper.toTrip(repos.tripRepository.save(new TripEntity()
					.setCode(trip.getCode())
					.setArriveStopCode(trip.getArriveCode())
					.setDepartStopCode(trip.getDepartCode())
					.setHaltStop1(trip.getHaltStop1())
					.setHaltStop2(trip.getHaltStop2())
					.setHaltTime(trip.getHaltTime())
					.setJourneyTime(trip.getJourneyTime())
					.setLastUpdate(DateUtils.today())
					.setOperator(repos.busOperatorRepository.findByOperatorCode(trip.getBusOperator().getOperatorCode())
							.get())
					.setVisible(true)));
		} else {
			return new Trip();
		}
	}

	@Override
	public Trip editTrip(Trip trip) {
		
		findByCode(trip);
		if(optional.isPresent()) {
			TripEntity tripEntity = optional.get();
			return TripMapper.toTrip(repos.tripRepository.save(tripEntity
					.setArriveStopCode(trip.getArriveCode())
					.setDepartStopCode(trip.getDepartCode())
					.setHaltStop1(trip.getHaltStop1())
					.setHaltStop2(trip.getHaltStop2())
					.setHaltTime(trip.getHaltTime())
					.setJourneyTime(trip.getJourneyTime())));
		} else {
			return new Trip();
		}
	}

	@Override
	public Trip viewTrip(Trip trip) {

		findByCode(trip);
		if(optional.isPresent()) {
			return TripMapper.toTrip(optional.get());
		} else {
			return new Trip();
		}
	}

	@Override
	public List<Trip> viewAllTrips() {

		List<TripEntity> tripList = repos.tripRepository.findAll();
		return TripMapper.toTrip(tripList);
	}

	@Override
	public List<Trip> viewAllTripsByOperator(BusOperator busOperator) {

		return TripMapper.toTrip(repos.tripRepository.findByOperator(repos.busOperatorRepository
				.findByOperatorCode(busOperator.getOperatorCode())
				.get()));
	}

	@Override
	public Trip disableTrip(Trip trip) {

		findByCode(trip);
		if(optional.isPresent()) {
			TripEntity tripEntity = optional.get();
			return TripMapper.toTrip(repos.tripRepository.save(tripEntity
					.setVisible(false)));
		} else {
			return new Trip();
		}

	}

	@Override
	public Trip enableTrip(Trip trip) {

		findByCode(trip);
		if(optional.isPresent()) {
			TripEntity tripEntity = optional.get();
			return TripMapper.toTrip(repos.tripRepository.save(tripEntity
					.setVisible(true)));
		} else {
			return new Trip();
		}
	}

}
