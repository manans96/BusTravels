package com.manan.busservice.dto.model.operator;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manan.busservice.dto.model.operations.Trip;
import com.manan.busservice.dto.model.user.User;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusOperator {
	
	@NotNull private String operatorName;
	@NotNull private String operatorCode;
	private String operatorDetails;
	private List<Bus> bus;
	private List<Trip> trip;
	@NotNull private Date lastUpdate;
	@NotNull private User operator;

}
