/**
 * 
 */
package com.manan.busservice.service.deprecated;

import java.util.List;

import com.manan.busservice.dto.model.operations.Ticket;
import com.manan.busservice.dto.model.operations.TripDetails;
import com.manan.busservice.dto.model.user.User;

/**
 * @author Manan Sanghvi
 *
 */
@Deprecated
public interface TicketService {
	
	Ticket newTicket(Ticket ticket);
	
	Ticket cancelTicket(Ticket ticket);
	
	Ticket viewTicket(Ticket ticket);
	
	List<Ticket> viewAllTicketByUser(User user);
	
	List<Ticket> viewAllTicketByTripDetails(TripDetails tripDetails);
	
	List<Ticket> viewAllTickets();

}
