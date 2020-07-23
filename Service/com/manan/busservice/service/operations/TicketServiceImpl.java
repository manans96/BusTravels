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
import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.user.User;
import com.manan.busservice.jpa.repository.Repositories;
import com.manan.busservice.model.operations.TicketEntity;
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
	
	private static Ticket ticket = new Ticket();
	private Optional<TicketEntity> optional;
	
	private void findByTicketNumber(Ticket ticket) {
		optional = repos.ticketRepository.findByTicketNumber(ticket.getTicketNumber());
	}

	@Override
	public Ticket newTicket(Ticket ticket) {

		findByTicketNumber(ticket);
		if(optional.isEmpty()) {
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
		} else {
			return TicketServiceImpl.ticket;
		}
	}

	@Override
	public Ticket cancelTicket(Ticket ticket) {

		findByTicketNumber(ticket);
		if(optional.isPresent() && optional.get().isCancellable() == true) {
			TicketEntity ticketEntity = optional.get();
			return TicketMapper.toTicket(repos.ticketRepository.save(ticketEntity
					.setCancelled(true)
					.setLastUpdate(DateUtils.today())
					));
		} else {
			return TicketServiceImpl.ticket;
		}
	}

	@Override
	public Ticket viewTicket(Ticket ticket) {

		findByTicketNumber(ticket);
		if(optional.isPresent()) {
			return TicketMapper.toTicket(optional.get());
		} else {
			return TicketServiceImpl.ticket;
		}
	}

	@Override
	public List<Ticket> viewAllTicketByUser(User user) {

		return TicketMapper.toTicket(repos.ticketRepository.findByPassenger(repos.userRepository
				.findByUserName(user
						.getUserName())
					.get()));
	}

	@Override
	public List<Ticket> viewAllTickets() {

		return TicketMapper.toTicket(repos.ticketRepository.findAll());
	}

	@Override
	public List<Ticket> viewAllTicketByTripDetails(TripDetails tripDetails) {

		return TicketMapper.toTicket(repos.ticketRepository.findByTripDetails(repos.tripDetailsRepository
				.findByTripDetailCode(tripDetails
						.getTripDetailCode())
					.get()));
	}

}
