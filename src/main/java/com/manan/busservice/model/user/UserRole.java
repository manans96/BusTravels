package com.manan.busservice.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.manan.busservice.model.operator.BusOperator;

import lombok.Data;

@Data
@Entity
@Table(name="userrole")
public class UserRole {
	
	@OneToOne(mappedBy = "role")
	@Column(name = "role", nullable = false) private User user;
	
	@OneToOne
	@Column(nullable = true)
	private BusOperator operator;

}
