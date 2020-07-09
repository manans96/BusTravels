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
import com.manan.busservice.model.user.User;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Booking")
	private int idBooking;
	
	@ManyToOne
	@JoinColumn(name = "code")
	@Column(nullable = false) private Trip tripCode;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	@Column(nullable = false) private User passenger;

	private Bus bus;
	
	private Trip trip;
	
	private Date departureTime;
	
	private int totalCost;

}
