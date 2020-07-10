package com.manan.busservice.model.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.manan.busservice.model.operations.BookingEntity;
import com.manan.busservice.model.operations.TicketEntity;
import com.manan.busservice.model.operator.BusOperatorEntity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private int idUser;
	@Column(nullable = false, unique = true) private String userName;
	@Column(nullable = false) private String firstName;
	@Column(nullable = false) private String lastName;
	@Column(nullable = false, unique = true) private String email;
	@Column(nullable = false, unique = true) private String phoneNo;
	@Column(nullable = false) private String role;
	
	@OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
	private List<TicketEntity> ticket;
	
	@OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
	private List<BookingEntity> booking;
	
	@PrimaryKeyJoinColumn
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private UserAuthEntity userAuth;
	
	@OneToOne(optional = true)
	private BusOperatorEntity operator;
	
}
