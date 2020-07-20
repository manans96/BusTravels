package com.manan.busservice.model.operations;

import java.util.Date;
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

import com.manan.busservice.model.operator.BusEntity;
import com.manan.busservice.model.operator.TripEntity;

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

	@Column(nullable = false, unique = true)
	private String tripDetailCode;
	
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
	
	@OneToMany(mappedBy = "tripDetails", cascade = CascadeType.PERSIST)
	private List<TicketEntity> tickets;
	
	@Column(nullable = false)
	private boolean isActive;

	@Column(nullable = false) private java.util.Date lastUpdate;

}
