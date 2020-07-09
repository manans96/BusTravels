package com.manan.busservice.model.operator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "bus")
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBus;
	
	@ManyToOne
	@JoinColumn(name = "id_operator")
	private BusOperator operator;
	
	private String busCode;
	
	private int capacity;
	
	private int cost;
	
	private int busModel;

}
