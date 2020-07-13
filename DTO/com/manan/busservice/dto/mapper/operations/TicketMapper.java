package com.manan.busservice.dto.mapper.operations;

import java.util.ArrayList;
import java.util.List;

import com.manan.busservice.dto.mapper.user.UserMapper;
import com.manan.busservice.dto.model.operations.Ticket;
import com.manan.busservice.model.operations.TicketEntity;

public class TicketMapper {
	
	public static Ticket toTicket(TicketEntity ticket) {
		
		return new Ticket()
				.setAmountPaid(ticket.getAmountPaid())
				.setCancellable(ticket.isCancellable())
				.setLastUpdate(ticket.getLastUpdate())
				.setTicketNumber(ticket.getTicketNumber())
				.setTotalTicket(ticket.getTotalTicket())
				.setPassenger(UserMapper.toUser(ticket.getPassenger()))
				.setTripDetails(TripDetailsMapper.toTripDetails(ticket.getTripDetails()));
		
	}

	public static List<Ticket> toTicket(List<TicketEntity> ticketList) {

		List<Ticket> tickets = new ArrayList<>();
		for(TicketEntity ticket : ticketList) {
			tickets.add(toTicket(ticket));
		}
		return tickets;
		
	}

}
