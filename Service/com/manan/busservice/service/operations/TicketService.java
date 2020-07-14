/**
 * 
 */
package com.manan.busservice.service.operations;

import java.util.List;

import com.manan.busservice.dto.model.operations.Ticket;
import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.user.User;

/**
 * @author Manan Sanghvi
 *
 */
public interface TicketService {
	
	Ticket newTicket(TripDetails tripDetails, Ticket ticket, User user);
	
	Ticket cancelTicket(Ticket ticket);
	
	Ticket viewTicket(Ticket ticket);
	
	List<Ticket> viewAllTicketByUser(User user);
	
	List<Ticket> viewAllTickets();

}
