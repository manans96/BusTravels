package com.manan.busservice.dto.model.operator;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.manan.busservice.dto.model.operations.Booking;
import com.manan.busservice.dto.model.operations.TripDetails;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {

	@JsonIgnoreProperties("bus")
	@NotNull private BusOperator operator;
	
	@NotNull private String busCode;
	@NotNull private int runCost;
	@NotNull private int haltCost;
	@NotNull private int capacity;
	@NotNull private boolean isAvailable;
	@NotNull private String busModel;
	private List<TripDetails> tripDetails;
	private List<Booking> booking;
	@NotNull private Date lastUpdate;

}
