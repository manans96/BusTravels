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

import com.manan.busservice.model.operations.BookingDB;
import com.manan.busservice.model.operations.TripDetailsDB;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "bus")
public class BusDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bus")
	private int idBus;
	
	@ManyToOne
	@JoinColumn(name = "id_operator")
	private BusOperatorDB operator;
	
	@Column(name = "bus_code", nullable = false, unique = true) private String busCode;
	
	@Column(name = "capacity", nullable = false) private int capacity;
	
	@Column(name = "run_cost", nullable = false) private int runCost;
	
	@Column(name = "halt_cost", nullable = false) private int haltCost;
	
	@Column(name = "model", nullable = false) private String busModel;
	
	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	private List<TripDetailsDB> tripDetails;
	
	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	private List<BookingDB> booking;

	@Column(nullable = false) private java.util.Date lastUpdate;

}
