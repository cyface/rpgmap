package com.cyface.rpg.map.client.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

@Entity
@Table(name = "point", schema="rpgmap")
@PersistenceUnit(name="rpgmap")
@NamedQuery(name="RPGMapPoint.getAll", query="SELECT point FROM RPGMapPoint as point ORDER BY name")
public class RPGMapPoint implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;

	@Id
	private int id;
	private double latitude;
	private double longitude;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}