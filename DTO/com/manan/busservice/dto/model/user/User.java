package com.manan.busservice.dto.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	@NotNull private String userName;
	@NotNull private String firstName;
	@NotNull private String lastName;
	@NotNull private String email;
	@NotNull private String phoneNo;
	@NotNull private String role;

}
