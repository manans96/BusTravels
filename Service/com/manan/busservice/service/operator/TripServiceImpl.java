/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operator.TripMapper;
import com.manan.busservice.dto.model.operator.Trip;
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operator.TripEntity;
import com.manan.busservice.response.EntityResponse;
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
	
	private void findByCode(String code) {
		optional = repos.tripRepository.findByCode(code);
	}

	@Override
	public Trip addTrip(Trip trip) {

		findByCode(trip.getCode());
		if(optional.isEmpty()) {
			try {
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
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(EntityResponse.TRIP);
			}
		}
		throw new BusAppException.DuplicateEntityException(EntityResponse.TRIP);
	}

	@Override
	public Trip editTrip(Trip trip) {
		
		findByCode(trip.getCode());
		if(optional.isPresent()) {
			try {
				TripEntity tripEntity = optional.get();
				return TripMapper.toTrip(repos.tripRepository.save(tripEntity
						.setArriveStopCode(trip.getArriveCode())
						.setDepartStopCode(trip.getDepartCode())
						.setHaltStop1(trip.getHaltStop1())
						.setHaltStop2(trip.getHaltStop2())
						.setHaltTime(trip.getHaltTime())
						.setJourneyTime(trip.getJourneyTime())));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(EntityResponse.TRIP);
			}
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.TRIP);
	}

	@Override
	public Trip viewTrip(String code) {

		findByCode(code);
		if(optional.isPresent()) {
			return TripMapper.toTrip(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.TRIP);
	}

	@Override
	public List<Trip> viewAllTrips() {

		List<TripEntity> tripList = repos.tripRepository.findAll();
		return TripMapper.toTrip(tripList);
	}

	@Override
	public List<Trip> viewAllTripsByOperator(String operatorCode) {

		return TripMapper.toTrip(repos.tripRepository.findByOperator(repos.busOperatorRepository
				.findByOperatorCode(operatorCode)
				.get()));
	}

	@Override
	public Trip disableTrip(String code) {

		findByCode(code);
		if(optional.isPresent()) {
			TripEntity tripEntity = optional.get();
			return TripMapper.toTrip(repos.tripRepository.save(tripEntity
					.setVisible(false)));
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.TRIP);

	}

	@Override
	public Trip enableTrip(String code) {

		findByCode(code);
		if(optional.isPresent()) {
			TripEntity tripEntity = optional.get();
			return TripMapper.toTrip(repos.tripRepository.save(tripEntity
					.setVisible(true)));
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.TRIP);
	}

}
