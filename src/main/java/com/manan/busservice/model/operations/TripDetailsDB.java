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

import com.manan.busservice.model.operator.BusDB;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="tripdetails")
public class TripDetailsDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trip_detail")
	private int idTripDetails;
	
	@ManyToOne
	@JoinColumn(name = "code")
	private TripDB tripCode;

	@ManyToOne
	@JoinColumn(name = "bus_code")
	private BusDB bus;
	
	private Date departureTime;
	
	private int cost;
	
	private int availableSeats;

	@Column(nullable = false) private java.util.Date lastUpdate;

}
