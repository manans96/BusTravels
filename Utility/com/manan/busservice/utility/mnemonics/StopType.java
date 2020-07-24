/**
 * 
 */
package com.manan.busservice.utility.mnemonics;

/**
 * @author Manan Sanghvi
 *
 */
public enum StopType {
	
	MAJOR("major"),
	MINOR("minor");
	
	String stopType;
	
	private StopType(String stopType) {
		this.stopType = stopType;
	}

	public String getStopType() {
		return stopType;
	}
}
