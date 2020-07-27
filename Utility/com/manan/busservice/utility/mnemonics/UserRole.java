/**
 * 
 */
package com.manan.busservice.utility.mnemonics;

/**
 * @author Manan Sanghvi
 *
 */
public enum UserRole {

	SUPERADMIN("superadmin"),
	ADMIN("admin"),
	OPERATOR("operator"),
	USER("user");
	
	public String roleString;

	private UserRole(String roleString) {
		this.roleString = roleString;
	}
	
	public String getRoleString() {
		return roleString;
	}
}
