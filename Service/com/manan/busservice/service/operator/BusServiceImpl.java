/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operator.BusMapper;
import com.manan.busservice.dto.model.operator.Bus;
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operator.BusEntity;
import com.manan.busservice.response.EntityResponse;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class BusServiceImpl implements Services.BusService {
	
	private Repositories.Container repos;
	
	@Autowired
	public BusServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	Optional<BusEntity> optional;
	
	private void findByCode(String busCode) {
		optional = repos.busRepository.findByBusCode(busCode);
	}

	@Override
	public Bus addBus(Bus bus) {

		findByCode(bus.getBusCode());
		if(optional.isEmpty()) {
			try {
				return BusMapper.toBus(repos.busRepository.save(new BusEntity()
						.setBusCode(bus.getBusCode())
						.setAvailable(bus.isAvailable())
						.setBusModel(bus.getBusModel())
						.setCapacity(bus.getCapacity())
						.setHaltCost(bus.getHaltCost())
						.setLastUpdate(DateUtils.today())
						.setRunCost(bus.getRunCost())
						.setOperator(repos.busOperatorRepository.findByOperatorCode(bus.getOperator().getOperatorCode())
								.get())));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(EntityResponse.BUS);
			}
		}
		throw new BusAppException.DuplicateEntityException(EntityResponse.BUS);
	}

	@Override
	public Bus editBus(Bus bus) {

		findByCode(bus.getBusCode());
		if(optional.isPresent()) {
			try {
				BusEntity busEntity = optional.get();
				return BusMapper.toBus(repos.busRepository.save(busEntity
						.setAvailable(bus.isAvailable())
						.setBusModel(bus.getBusModel())
						.setCapacity(bus.getCapacity())
						.setHaltCost(bus.getHaltCost())
						.setLastUpdate(DateUtils.today())
						.setRunCost(bus.getRunCost())));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(EntityResponse.BUS);
			}
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.BUS);
	}

	@Override
	public Bus viewBus(String busCode) {

		findByCode(busCode);
		if(optional.isPresent()) {
			return BusMapper.toBus(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.BUS);
	}

	@Override
	public List<Bus> viewAllBus() {

		List<BusEntity> busList = repos.busRepository.findAll();
		return BusMapper.toBus(busList);
	}

	@Override
	public List<Bus> viewAllBusByOperator(String operatorCode) {

		return BusMapper.toBus(repos.busRepository.findByOperator(repos.busOperatorRepository
				.findByOperatorCode(operatorCode)
				.get()));
	}

	@Override
	public Bus disableBus(String busCode) {

		findByCode(busCode);
		if(optional.isPresent()) {
			BusEntity busEntity = optional.get();
			return BusMapper.toBus(repos.busRepository.save(busEntity
					.setAvailable(false)));
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.BUS);
	}

	@Override
	public Bus enableBus(String busCode) {

		findByCode(busCode);
		if(optional.isPresent()) {
			BusEntity busEntity = optional.get();
			return BusMapper.toBus(repos.busRepository.save(busEntity
					.setAvailable(true)));
		}
		throw new BusAppException.EntityNotFoundException(EntityResponse.BUS);
	}

}
