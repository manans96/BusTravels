package com.manan.busservice.model.operator;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.manan.busservice.model.user.UserRole;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="operator")
public class BusOperator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOperator;
	
	@OneToOne(mappedBy = "operator")
	@Column(name = "name", nullable = false) private UserRole operatorName;
	
	@Column(name = "code", nullable = false)
	private String operatoreCode;
	
	@Column(name = "details")
	private String operatorDetails;
	
	@OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
	private List<Bus> bus;

}
