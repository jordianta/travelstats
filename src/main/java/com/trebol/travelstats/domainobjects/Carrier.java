package com.trebol.travelstats.domainobjects;


public class Carrier {
	
	private Integer	id;
	private String	name;
	private String	iataCode;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
