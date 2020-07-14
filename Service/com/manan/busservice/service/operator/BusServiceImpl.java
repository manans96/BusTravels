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
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.jpa.repository.BusOperatorRepository;
import com.manan.busservice.jpa.repository.BusRepository;
import com.manan.busservice.model.operator.BusEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class BusServiceImpl implements BusService {
	
	private BusOperatorRepository busOperatorRepository;
	private BusRepository busRepository;
	
	/**
	 * 
	 */
	@Autowired
	public BusServiceImpl(BusOperatorRepository busOperatorRepository, BusRepository busRepository) {
		this.busOperatorRepository = busOperatorRepository;
		this.busRepository = busRepository;
	}
	
	Optional<BusEntity> optional;
	
	private void findByCode(Bus bus) {
		optional = busRepository.findByBusCode(bus.getBusCode());
	}

	@Override
	public Bus addBus(Bus bus) {

		findByCode(bus);
		if(optional.isEmpty()) {
			return BusMapper.toBus(busRepository.save(new BusEntity()
					.setBusCode(bus.getBusCode())
					.setAvailable(bus.isAvailable())
					.setBusModel(bus.getBusModel())
					.setCapacity(bus.getCapacity())
					.setHaltCost(bus.getHaltCost())
					.setLastUpdate(DateUtils.today())
					.setRunCost(bus.getRunCost())
					.setOperator(busOperatorRepository.findByOperatorCode(bus.getOperator().getOperatorCode()).get())));
		} else {
			return new Bus();
		}
	}

	@Override
	public Bus editBus(Bus bus) {

		findByCode(bus);
		if(optional.isPresent()) {
			BusEntity busEntity = optional.get();
			return BusMapper.toBus(busRepository.save(busEntity
					.setAvailable(bus.isAvailable())
					.setBusModel(bus.getBusModel())
					.setCapacity(bus.getCapacity())
					.setHaltCost(bus.getHaltCost())
					.setLastUpdate(DateUtils.today())
					.setRunCost(bus.getRunCost())));
		} else {
			return new Bus();
		}
	}

	@Override
	public Bus viewBus(Bus bus) {

		findByCode(bus);
		return BusMapper.toBus(optional.get());
	}

	@Override
	public List<Bus> viewAllBus() {

		List<BusEntity> busList = busRepository.findAll();
		return BusMapper.toBus(busList);
	}

	@Override
	public List<Bus> viewAllBusByOperator(BusOperator busOperator) {

		return BusMapper.toBus(busRepository.findByOperator(busOperatorRepository
				.findByOperatorCode(busOperator.getOperatorCode())
				.get().getIdOperator()));
	}

	@Override
	public Bus disableBus(Bus bus) {

		findByCode(bus);
		if(optional.isPresent()) {
			BusEntity busEntity = optional.get();
			return BusMapper.toBus(busRepository.save(busEntity
					.setAvailable(false)));
		} else {
			return new Bus();
		}
	}

	@Override
	public Bus enableBus(Bus bus) {

		findByCode(bus);
		if(optional.isPresent()) {
			BusEntity busEntity = optional.get();
			return BusMapper.toBus(busRepository.save(busEntity
					.setAvailable(true)));
		} else {
			return new Bus();
		}
	}

}
