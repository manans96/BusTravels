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
import javax.persistence.Table;

import com.manan.busservice.model.operations.Booking;
import com.manan.busservice.model.operations.Ticket;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private int idUser;
	@Column(nullable = false) private String userName;
	@Column(nullable = false) private String firstName;
	@Column(nullable = false) private String lastName;
	@Column(nullable = false) private String email;
	@Column(nullable = false) private String phoneNo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@Column(nullable = false) private UserRole role;
	
	@OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
	private List<Ticket> ticket;
	
	@OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
	private List<Booking> booking;
	
}
