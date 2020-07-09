package com.manan.busservice.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="userrole")
public class UserRole {
	
	@OneToOne
	@Column(name = "role", nullable = false) private User user;
	private String operatorName;

}
