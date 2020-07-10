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
import com.manan.busservice.model.user.UserDB;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="booking")
public class BookingDB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Booking")
	private int idBooking;
	
	@ManyToOne
	@JoinColumn(name = "code")
	private TripDB tripCode;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private UserDB passenger;

	@ManyToOne
	@JoinColumn(name = "bus_code")
	private BusDB bus;
	
	@Column(nullable = false)
	private Date departureTime;

	@Column(nullable = false)
	private int totalCost;

	@Column(nullable = false) private java.util.Date lastUpdate;

}
