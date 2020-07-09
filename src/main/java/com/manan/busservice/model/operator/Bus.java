package com.manan.busservice.model.operator;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.manan.busservice.model.operations.TripDetails;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "bus")
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bus")
	private int idBus;
	
	@ManyToOne
	@JoinColumn(name = "id_operator")
	private BusOperator operator;
	
	@Column(name = "code", nullable = false) private String busCode;
	
	@Column(name = "capacity", nullable = false) private int capacity;
	
	@Column(name = "run_cost", nullable = false) private int runCost;
	
	@Column(name = "halt_cost", nullable = false) private int haltCost;
	
	@Column(name = "model", nullable = false) private String busModel;
	
	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	private List<TripDetails> tripDetails;

}
