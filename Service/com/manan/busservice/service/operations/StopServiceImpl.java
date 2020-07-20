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
import com.manan.busservice.jpa.repository.StopRepository;
import com.manan.busservice.model.operations.StopEntity;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class StopServiceImpl implements StopService {
	
	private StopRepository stopRepository;
	
	@Autowired
	public StopServiceImpl(StopRepository stopRepository) {
		this.stopRepository = stopRepository;
	}
	
	private Optional<StopEntity> optional;
	
	private void findByStopCode(Stop stop) {
		optional = stopRepository.findByStopCode(stop.getStopCode());
	}

	@Override
	public Stop addStop(Stop stop) {

		findByStopCode(stop);
		if(optional.isEmpty()) {
			return StopMapper.toStop(stopRepository.save(new StopEntity()
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
			return StopMapper.toStop(stopRepository.save(stopEntity
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

		return StopMapper.toStop(stopRepository.findAll());
	}

}
