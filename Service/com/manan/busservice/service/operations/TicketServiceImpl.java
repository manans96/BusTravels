/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manan.busservice.dto.mapper.operations.TicketMapper;
import com.manan.busservice.dto.model.operations.Ticket;
import com.manan.busservice.exception.BusAppException;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.TicketEntity;
import com.manan.busservice.response.ResponseEntity;
import com.manan.busservice.service.Services;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class TicketServiceImpl implements Services.TicketService {
	
	private Repositories.Container repos;
	
	@Autowired
	public TicketServiceImpl(Repositories.Container repos) {
		this.repos = repos;
	}
	
	private Optional<TicketEntity> optional;
	
	private void findByTicketNumber(String ticketNumber) {
		optional = repos.ticketRepository.findByTicketNumber(ticketNumber);
	}

	@Override
	public Ticket newTicket(Ticket ticket) {

		findByTicketNumber(ticket.getTicketNumber());
		if(optional.isEmpty()) {
			try {
				return TicketMapper.toTicket(repos.ticketRepository.save(new TicketEntity()
						.setTicketNumber(ticket.getTicketNumber())
						.setAmountPaid(ticket.getAmountPaid())
						.setLastUpdate(DateUtils.today())
						.setCancellable(ticket.isCancellable())
						.setCancelled(false)
						.setTotalTicket(ticket.getTotalTicket())
						.setTripDetails(repos.tripDetailsRepository.findByTripDetailCode(ticket
								.getTripDetails()
								.getTripDetailCode())
								.get())
						.setPassenger(repos.userRepository.findByUserName(ticket
								.getPassenger()
								.getUserName())
								.get())));
			} catch(RuntimeException re) {
				throw new BusAppException.BadRequestException(ResponseEntity.TICKET);
			}
		}
		throw new BusAppException.DuplicateEntityException(ResponseEntity.TICKET);
	}

	@Override
	public Ticket cancelTicket(String ticketNumber) {

		findByTicketNumber(ticketNumber);
		if(optional.isPresent() && optional.get().isCancellable() == true) {
			TicketEntity ticketEntity = optional.get();
			return TicketMapper.toTicket(repos.ticketRepository.save(ticketEntity
					.setCancelled(true)
					.setLastUpdate(DateUtils.today())
					));
		} else if(optional.get().isCancellable() == false) {
			throw new BusAppException.ForbiddenException(ResponseEntity.TICKET);
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.TICKET);
	}

	@Override
	public Ticket viewTicket(String ticketNumber) {

		findByTicketNumber(ticketNumber);
		if(optional.isPresent()) {
			return TicketMapper.toTicket(optional.get());
		}
		throw new BusAppException.EntityNotFoundException(ResponseEntity.TICKET);
	}

	@Override
	public List<Ticket> viewAllTicketByUser(String userName) {

		return TicketMapper.toTicket(repos.ticketRepository.findByPassenger(repos.userRepository
				.findByUserName(userName)
					.get()));
	}

	@Override
	public List<Ticket> viewAllTickets() {

		return TicketMapper.toTicket(repos.ticketRepository.findAll());
	}

	@Override
	public List<Ticket> viewAllTicketByTripDetails(String tripDetailCode) {

		return TicketMapper.toTicket(repos.ticketRepository.findByTripDetails(repos.tripDetailsRepository
				.findByTripDetailCode(tripDetailCode)
					.get()));
	}

}
