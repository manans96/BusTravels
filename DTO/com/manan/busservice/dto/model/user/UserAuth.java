package com.manan.busservice.dto.model.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuth {
	
	@NotNull private String password;
	
	@NotNull private Date lastUpdate;

}
