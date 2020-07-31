/**
 * 
 */
package com.manan.busservice.controller.v1.controller.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Manan Sanghvi
 *
 */
@Data
@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequest {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private String password;

}
