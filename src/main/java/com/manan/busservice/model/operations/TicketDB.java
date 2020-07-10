package com.manan.busservice.model.operations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.manan.busservice.model.user.UserDB;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="ticket")
public class TicketDB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Ticket")
	private int idTicket;
	
	@Column(nullable = false) private int amountPaid;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserDB passenger;
	
	@Column(nullable = false) private int totalTicket;
	
	@Column(nullable = false) private String ticketNumber;
	
	@Column(nullable = false) private boolean isCancellable;

	@Column(nullable = false) private java.util.Date lastUpdate;
	
}
