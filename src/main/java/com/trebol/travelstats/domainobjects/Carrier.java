package com.trebol.travelstats.domainobjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carrier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

    @Column(nullable = false)
	private String	name;

    @Column(nullable = false)
	private String	iataCode;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIataCode() {
		return iataCode;
	}
	
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

}
