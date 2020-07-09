package com.manan.busservice.model.operations;

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

import com.manan.busservice.model.operator.BusOperator;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="trip")
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trip")
	private int idTrip;
	
	@Column(nullable = false) private String code;
	
	@Column(nullable = false) private String departStopCode;

	@Column(nullable = false) private String arriveStopCode;

	private String haltStop1;
	private String haltStop2;
	
	@Column(nullable = false) private int journeyTime;
	
	@Column(nullable = false) private int haltTime;
	
	@Column(nullable = false) private boolean isVisible;
	
	@ManyToOne
	@JoinColumn(name = "id_operator")
	private BusOperator operator;
	
	@OneToMany(mappedBy = "tripCode", cascade = CascadeType.ALL)
	private List<TripDetails> tripDetails;
	
	@OneToMany(mappedBy = "tripCode", cascade = CascadeType.ALL)
	private List<Booking> booking;

}
