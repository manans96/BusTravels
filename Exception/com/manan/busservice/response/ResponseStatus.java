/**
 * 
 */
package com.manan.busservice.response;

/**
 * @author Manan Sanghvi
 *
 */
public enum ResponseStatus {
	
	OK("OK"),
	BAD_REQUEST("BAD_REQUEST"),
	UNAUTHORIZED("UNAUTHORIZED"),
	VALIDATION_EXCEPTION("VALIDATION_EXCEPTION"),
	EXCEPTION("EXCEPTION"),
	WRONG_CREDENTIALS("WRONG_CREDENTIALS"),
	ACCESS_DENIED("ACCESS_DENIED"),
	NOT_FOUND("NOT_FOUND"),
	DUPLICATE_ENTITY("DUPLICATE_ENTITY"),
	INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR");
	
	private String status;
	
	ResponseStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	

}
