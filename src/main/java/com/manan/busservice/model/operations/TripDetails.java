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

import com.manan.busservice.model.operator.Bus;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="tripdetails")
public class TripDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trip_detail")
	private int idTripDetails;
	
	@ManyToOne
	@JoinColumn(name = "code")
	@Column(nullable = false) private Trip tripCode;

	@ManyToOne
	@JoinColumn(name = "code")
	private Bus bus;
	
	private Date departureTime;
	
	private int cost;
	
	private int availableSeats;

}
