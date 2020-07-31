/**
 * 
 */
package com.manan.busservice.controller.v1.controller.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Manan Sanghvi
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWTResponse {
	
	private String token;
	private String message;
}
