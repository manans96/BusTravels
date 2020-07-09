package com.manan.busservice.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;
	@Column(nullable = false) private String userName;
	@Column(nullable = false) private String firstName;
	@Column(nullable = false) private String lastName;
	@Column(nullable = false) private String email;
	@Column(nullable = false) private String phoneNo;
	
	@OneToOne(mappedBy = "user")
	@Column(nullable = false) private UserRole role;
	
}
