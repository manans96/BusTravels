package com.manan.busservice.model.operations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name="stop")
public class Stop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stop")
	private int idStop;
	
	@Column(name = "name", nullable = false) private String stopName;
	
	@Column(name = "type", nullable = false) private String stopType;
	
	@Column(name = "code", nullable = false) private String stopCode;

}
