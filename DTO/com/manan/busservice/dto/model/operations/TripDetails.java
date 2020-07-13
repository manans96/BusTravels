package com.manan.busservice.dto.model.operations;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manan.busservice.dto.model.operator.Bus;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripDetails {
	
	@NotNull private Trip tripCode;
	@NotNull private Bus bus;
	@NotNull private Date departureTime;
	@NotNull private int cost;
	@NotNull private int availableSeats;
	@NotNull private boolean isActive;
	@NotNull private Date lastUpdate;

}
