package com.cyface.rpg.map.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

import net.sf.hibernate4gwt.pojo.java5.LazyPojo;

@Entity
@Table(name = "point", schema = "rpgmap")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapPoint.getAll", query = "SELECT point FROM RPGMapPoint as point ORDER BY name")
public class RPGMapPoint extends LazyPojo implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;

	private int id;
	private RPGMapMap parentRPGMapMap;
	private double latitude;
	private double longitude;
	private String name;

	public String toString() {
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append("Point ID: ");
		outputBuffer.append(getId());
		outputBuffer.append("\tName: ");
		outputBuffer.append(getName());
		outputBuffer.append("\tLat/Lng: ");
		outputBuffer.append(getLatitude());
		outputBuffer.append("/");
		outputBuffer.append(getLongitude());
		outputBuffer.append("\tParent Map ID/Name: ");
		outputBuffer.append(getParentRPGMapMap().getId());
		outputBuffer.append("/");
		outputBuffer.append(getParentRPGMapMap().getName());

		return outputBuffer.toString();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne
	@JoinColumn(name = "parent_map_id")
	public RPGMapMap getParentRPGMapMap() {
		return parentRPGMapMap;
	}

	public void setParentRPGMapMap(RPGMapMap parentRPGMapMap) {
		this.parentRPGMapMap = parentRPGMapMap;
	}
}
