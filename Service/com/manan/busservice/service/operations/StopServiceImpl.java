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
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.StopEntity;
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
	
	private void findByStopCode(Stop stop) {
		optional = repos.stopRepository.findByStopCode(stop.getStopCode());
	}

	@Override
	public Stop addStop(Stop stop) {

		findByStopCode(stop);
		if(optional.isEmpty()) {
			return StopMapper.toStop(repos.stopRepository.save(new StopEntity()
					.setStopCode(stop.getStopCode())
					.setStopName(stop.getStopName())
					.setStopType(stop.getStopType())));
		} else {
			return new Stop();
		}
	}

	@Override
	public Stop editStop(Stop stop) {

		findByStopCode(stop);
		if(optional.isPresent()) {
			StopEntity stopEntity = optional.get();
			return StopMapper.toStop(repos.stopRepository.save(stopEntity
					.setStopName(stop.getStopName())
					.setStopType(stop.getStopType())));
		} else {
			return new Stop();
		}
	}

	@Override
	public Stop findStop(Stop stop) {

		findByStopCode(stop);
		return StopMapper.toStop(optional.get());
	}

	@Override
	public List<Stop> listAllStops() {

		return StopMapper.toStop(repos.stopRepository.findAll());
	}

}
