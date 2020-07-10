package com.manan.busservice.model.operations;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.manan.busservice.model.operator.BusEntity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="tripdetails")
public class TripDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trip_detail")
	private int idTripDetails;
	
	@ManyToOne
	@JoinColumn(name = "code")
	private TripEntity tripCode;

	@ManyToOne
	@JoinColumn(name = "bus_code")
	private BusEntity bus;

	@Column(nullable = false)
	private Date departureTime;

	@Column(nullable = false)
	private int cost;

	@Column(nullable = false)
	private int availableSeats;

	@Column(nullable = false) private java.util.Date lastUpdate;

}