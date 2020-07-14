/**
 * 
 */
package com.manan.busservice.service.operator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operator.BusOperatorMapper;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.jpa.repository.BusOperatorRepository;
import com.manan.busservice.jpa.repository.UserRepository;
import com.manan.busservice.model.operator.BusOperatorEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class BusOperatorServiceImpl implements BusOperatorService {
	
	private BusOperatorRepository busOperatorRepository;
	private UserRepository userRepository;
	
	/**
	 * 
	 */
	@Autowired
	public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepository, UserRepository userRepository) {
		this.busOperatorRepository = busOperatorRepository;
		this.userRepository = userRepository;
	}
	
	private Optional<BusOperatorEntity> optional;
	
	private void findByOperator(BusOperator busOperator) {
		optional = busOperatorRepository.findByOperatorCode(busOperator.getOperatorCode());
	}

	@Override
	public BusOperator addNewOperator(BusOperator busOperator, User user) {

		findByOperator(busOperator);
		if(optional.isEmpty()) {
			return BusOperatorMapper.toBusOperator(busOperatorRepository.save(new BusOperatorEntity()
					.setOperatorName(busOperator.getOperatorName())
					.setLastUpdate(DateUtils.today())
					.setOperatorCode(busOperator.getOperatorCode())
					.setOperatorDetails(busOperator.getOperatorDetails())
					.setOperator(userRepository.findByUserName(user.getUserName()).get())));
		} else {
			return new BusOperator();
		}
	}

	@Override
	public BusOperator updateBusOperatorDetails(BusOperator busOperator) {

		findByOperator(busOperator);
		if(!optional.isEmpty()) {
			BusOperatorEntity busOperatorEntity = optional.get();
			return BusOperatorMapper.toBusOperator(busOperatorRepository.save(busOperatorEntity)
					.setOperatorDetails(busOperator.getOperatorDetails()));
		} else {
			return new BusOperator();
		}
	}

	@Override
	public void deleteBusOperator(BusOperator busOperator) {

		findByOperator(busOperator);
		if(!optional.isEmpty()) {
			busOperatorRepository.delete(optional.get());
		}

	}

	@Override
	public BusOperator viewBusOperator(BusOperator busOperator) {

		findByOperator(busOperator);
		if(optional.isPresent()) {
			return BusOperatorMapper.toBusOperator(optional.get());
		} else {
			return new BusOperator();
		}
	}

	@Override
	public List<BusOperator> viewAllBusOperators() {

		return BusOperatorMapper.toBusOperator(busOperatorRepository.findAll());
	}

}
