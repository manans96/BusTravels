/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operations.StopMapper;
import com.manan.busservice.dto.model.operations.Stop;
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.StopEntity;
import com.manan.busservice.response.ResponseEntity;
import com.manan.busservice.service.Services;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class StopServiceImpl implements Services.StopService {
	
	private Repositories.Container repos;
	
	@Autowired
	public StopServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	private Optional<StopEntity> optional;
	
	private void findByStopCode(String stopCode) {
		optional = repos.stopRepository.findByStopCode(stopCode);
	}

	@Override
	public Stop addStop(Stop stop) {

		findByStopCode(stop.getStopCode());
		if(optional.isEmpty()) {
			try {
				return StopMapper.toStop(repos.stopRepository.save(new StopEntity()
						.setStopCode(stop.getStopCode())
						.setStopName(stop.getStopName())
						.setStopType(stop.getStopType().getStopType())));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.STOP);
			}
		}
		throw new BusAppException.DuplicateEntityException(ResponseEntity.STOP);
	}

	@Override
	public Stop editStop(Stop stop) {

		findByStopCode(stop.getStopCode());
		if(optional.isPresent()) {
			try {
				StopEntity stopEntity = optional.get();
				return StopMapper.toStop(repos.stopRepository.save(stopEntity
						.setStopName(stop.getStopName())
						.setStopType(stop.getStopType().getStopType())));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.STOP);
			}
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.STOP);
	}

	@Override
	public Stop findStop(String stopCode) {

		findByStopCode(stopCode);
		if(optional.isPresent()) {
			return StopMapper.toStop(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.STOP);		
	}

	@Override
	public List<Stop> listAllStops() {

		return StopMapper.toStop(repos.stopRepository.findAll());
	}

}
