package com.trebol.travelstats.domainobjects;

import java.sql.Time;
import java.util.Date;

public class Flight {

	private Integer		id;
	private Integer		origin;
	private Integer		destination;
	private Integer		carrier;
	private Date		date;
	private Integer		distance;
	private Time	duration;
	private String		number;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public Integer getDestination() {
		return destination;
	}

	public void setDestination(Integer destination) {
		this.destination = destination;
	}

	public Integer getCarrier() {
		return carrier;
	}

	public void setCarrier(Integer carrier) {
		this.carrier = carrier;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
