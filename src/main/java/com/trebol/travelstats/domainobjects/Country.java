package com.trebol.travelstats.domainobjects;

public class Country {

	private Integer	id;
	private String	name;
	private Integer	continentId;
	private String	isoCode;

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

	public Integer getContinentId() {
		return continentId;
	}

	public void setContinentId(Integer continentId) {
		this.continentId = continentId;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

}
