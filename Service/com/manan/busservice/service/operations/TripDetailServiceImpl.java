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
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.TripDetailsEntity;
import com.manan.busservice.response.ResponseEntity;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class TripDetailServiceImpl implements Services.TripDetailService {
	
	private Repositories.Container repos;
	
	@Autowired
	public TripDetailServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	private Optional<TripDetailsEntity> optional;
	
	private void findByTripDetailsCode(String tripDetailCode) {
		optional = repos.tripDetailsRepository.findByTripDetailCode(tripDetailCode);
	}

	@Override
	public TripDetails addNewJourney(TripDetails tripDetails) {
		
		findByTripDetailsCode(tripDetails.getTripDetailCode());
		if(optional.isEmpty()) {
			try {
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
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.TRIPDETAILS);
			}
		}
		throw new BusAppException.DuplicateEntityException(ResponseEntity.TRIPDETAILS);
	}

	@Override
	public TripDetails editJourney(TripDetails tripDetails) {

		findByTripDetailsCode(tripDetails.getTripDetailCode());
		if(optional.isPresent()) {
			try {
				TripDetailsEntity tripDetailsEntity = optional.get();
				return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.save(tripDetailsEntity
						.setAvailableSeats(tripDetails.getAvailableSeats())
						.setCost(tripDetails.getCost())
						.setDepartureTime(tripDetails.getDepartureTime())
						.setLastUpdate(tripDetails.getLastUpdate())
						.setBus(repos.busRepository.findByBusCode(tripDetails.getBus().getBusCode())
								.get())
						));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.TRIPDETAILS);
			}
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.TRIPDETAILS);
	}

	@Override
	public TripDetails disableTrip(String tripDetailCode) {

		findByTripDetailsCode(tripDetailCode);
		if(optional.isPresent()) {
			TripDetailsEntity tripDetailsEntity = optional.get();
			return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.save(tripDetailsEntity
					.setActive(false)
					.setLastUpdate(DateUtils.today())));
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.TRIPDETAILS);
	}

	@Override
	public TripDetails enableTrip(String tripDetailCode) {

		findByTripDetailsCode(tripDetailCode);
		if(optional.isPresent()) {
			TripDetailsEntity tripDetailsEntity = optional.get();
			return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.save(tripDetailsEntity
					.setActive(true)
					.setLastUpdate(DateUtils.today())));
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.TRIPDETAILS);
	}

	@Override
	public TripDetails viewTrip(String tripDetailCode) {

		findByTripDetailsCode(tripDetailCode);
		if(optional.isPresent()) {
			return TripDetailsMapper.toTripDetails(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.TRIPDETAILS);		
	}

	@Override
	public List<TripDetails> viewAllTrips() {

		return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.findAll());
	}

	@Override
	public List<TripDetails> viewTripDetailsByTrip(String tripCode) {

		return TripDetailsMapper.toTripDetails(repos.tripDetailsRepository.findByTripCode(repos.tripRepository.findByCode(tripCode)
				.get()));
	}

}
