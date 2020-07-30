/**
 * 
 */
package com.manan.busservice.controller.v1.request.user;

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
public class LoginRequest {

	private String userName;
	private String password;
}
