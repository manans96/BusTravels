/**
 * 
 */
package com.manan.busservice.response;

/**
 * @author Manan Sanghvi
 *
 */
public enum ResponseEntity {
	
	USER("user"),
	BUS("bus"),
	BUSOPERATOR("bus operator"),
	TRIP("trip"),
	BOOKING("booking"),
	STOP("stop"),
	TICKET("ticket"),
	TRIPDETAILS("trip details");
	
	private String responseEntity;
	
	ResponseEntity(String responseEntity) {
		this.responseEntity = responseEntity;
	}

	public String getResponseEntity() {
		return responseEntity;
	}
}
