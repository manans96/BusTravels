package com.manan.busservice.dto.model.operations;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manan.busservice.dto.model.operator.BusOperator;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trip {
	
	@NotNull private String code;
	@NotNull private String departCode;
	@NotNull private String arriveCode;
	private String haltStop1;
	private String haltStop2;
	@NotNull private int journeyTime;
	@NotNull private int haltTime;
	@NotNull private boolean isVisible;
	@NotNull private BusOperator busOperator;
	private List<TripDetails> tripDetails;
	private List<Booking> booking;
	@NotNull private Date lastUpdate;

}
