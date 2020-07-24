package com.manan.busservice.dto.model.operations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manan.busservice.utility.mnemonics.StopType;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stop {
	
	@NotNull private String stopName;
	@NotNull private StopType stopType;
	@NotNull private String stopCode;

}
