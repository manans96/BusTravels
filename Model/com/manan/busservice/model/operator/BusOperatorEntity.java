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

import com.manan.busservice.model.operations.TripEntity;
import com.manan.busservice.model.user.UserEntity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="operator")
public class BusOperatorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_operator")
	private int idOperator;
	
	@OneToOne(optional = true)
	private UserEntity operator;
	
	@Column(name = "name", nullable = false, unique = true)
	private String OperatorName;
	
	@Column(name = "code", nullable = false, unique = true)
	private String operatorCode;
	
	@Column(name = "details")
	private String operatorDetails;
	
	@OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
	private List<BusEntity> bus;

	@OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
	private List<TripEntity> trip;

	@Column(nullable = false) private java.util.Date lastUpdate;

}
