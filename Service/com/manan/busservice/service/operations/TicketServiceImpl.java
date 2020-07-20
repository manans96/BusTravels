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
import com.manan.busservice.jpa.repository.TicketRepository;
import com.manan.busservice.jpa.repository.TripDetailsRepository;
import com.manan.busservice.jpa.repository.UserRepository;
import com.manan.busservice.model.operations.TicketEntity;
import com.manan.busservice.utility.DateUtils;

/**
 * @author Manan Sanghvi
 *
 */
@Component
public class TicketServiceImpl implements TicketService {
	
	private TicketRepository ticketRepository;
	private UserRepository userRepository;
	private TripDetailsRepository tripDetailsRepository;
	
	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, TripDetailsRepository tripDetailsRepository) {
		this.ticketRepository = ticketRepository;
		this.userRepository = userRepository;
		this.tripDetailsRepository = tripDetailsRepository;
	}
	
	private static Ticket ticket = new Ticket();
	private Optional<TicketEntity> optional;
	
	private void findByTicketNumber(Ticket ticket) {
		optional = ticketRepository.findByTicketNumber(ticket.getTicketNumber());
	}

	@Override
	public Ticket newTicket(Ticket ticket) {

		findByTicketNumber(ticket);
		if(optional.isEmpty()) {
			return TicketMapper.toTicket(ticketRepository.save(new TicketEntity()
					.setTicketNumber(ticket.getTicketNumber())
					.setAmountPaid(ticket.getAmountPaid())
					.setLastUpdate(DateUtils.today())
					.setCancellable(ticket.isCancellable())
					.setCancelled(false)
					.setTotalTicket(ticket.getTotalTicket())
					.setTripDetails(tripDetailsRepository.findByTripDetailCode(ticket
							.getTripDetails()
							.getTripDetailCode())
							.get())
					.setPassenger(userRepository.findByUserName(ticket
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
			return TicketMapper.toTicket(ticketRepository.save(ticketEntity
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

		return TicketMapper.toTicket(ticketRepository.findByPassenger(userRepository
				.findByUserName(user
						.getUserName())
					.get()));
	}

	@Override
	public List<Ticket> viewAllTickets() {

		return TicketMapper.toTicket(ticketRepository.findAll());
	}

	@Override
	public List<Ticket> viewAllTicketByTripDetails(TripDetails tripDetails) {

		return TicketMapper.toTicket(ticketRepository.findByTripDetails(tripDetailsRepository
				.findByTripDetailCode(tripDetails
						.getTripDetailCode())
					.get()));
	}

}
