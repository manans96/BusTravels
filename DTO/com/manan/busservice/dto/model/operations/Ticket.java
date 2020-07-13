package com.manan.busservice.dto.model.operations;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manan.busservice.dto.model.user.User;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
	
	@NotNull private int amountPaid;
	@NotNull private User passenger;
	@NotNull private int totalTicket;
	@NotNull private String ticketNumber;
	@NotNull private boolean isCancellable;
	@NotNull private boolean isCancelled;
	@NotNull private Date lastUpdate;
	@NotNull private TripDetails tripDetails;

}
