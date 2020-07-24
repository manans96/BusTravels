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
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operator.BusOperatorEntity;
import com.manan.busservice.response.ResponseEntity;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class BusOperatorServiceImpl implements Services.BusOperatorService {
	
	private Repositories.Container repos;
	
	/**
	 * 
	 */
	@Autowired
	public BusOperatorServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	private Optional<BusOperatorEntity> optional;
	
	private void findByOperatorCode(String code) {
		optional = repos.busOperatorRepository.findByOperatorCode(code);
	}

	@Override
	public BusOperator addNewOperator(BusOperator busOperator) {

		findByOperatorCode(busOperator.getOperatorCode());
		if(optional.isEmpty()) {
			try {
				return BusOperatorMapper.toBusOperator(repos.busOperatorRepository.save(new BusOperatorEntity()
						.setOperatorName(busOperator.getOperatorName())
						.setLastUpdate(DateUtils.today())
						.setOperatorCode(busOperator.getOperatorCode())
						.setOperatorDetails(busOperator.getOperatorDetails())
						.setOperator(repos.userRepository.findByUserName(busOperator.getOperator().getUserName()).get().setRole("operator"))));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.BUSOPERATOR);
			}
		}
		throw new BusAppException.DuplicateEntityException(ResponseEntity.BUSOPERATOR);
	}

	@Override
	public BusOperator updateBusOperatorDetails(BusOperator busOperator) {

		findByOperatorCode(busOperator.getOperatorCode());
		if(optional.isPresent()) {
			try {
				BusOperatorEntity busOperatorEntity = optional.get();
				return BusOperatorMapper.toBusOperator(repos.busOperatorRepository.save(busOperatorEntity)
						.setOperatorDetails(busOperator.getOperatorDetails()));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.BUSOPERATOR);
			}
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.BUSOPERATOR);
	}

	@Override
	public void deleteBusOperator(String operatorCode) {

		findByOperatorCode(operatorCode);
		if(optional.isPresent()) {
			repos.busOperatorRepository.delete(optional.get());
		} else {
			throw new BusAppException.EntityNotFoundException(ResponseEntity.BUSOPERATOR);
		}

	}

	@Override
	public BusOperator viewBusOperator(String operatorCode) {

		findByOperatorCode(operatorCode);
		if(optional.isPresent()) {
			return BusOperatorMapper.toBusOperator(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.BUSOPERATOR);
	}

	@Override
	public List<BusOperator> viewAllBusOperators() {

		return BusOperatorMapper.toBusOperator(repos.busOperatorRepository.findAll());
	}

}
