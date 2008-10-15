package com.cyface.rpg.map.server;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="map.getAll", query="select map from map")
@Table(name = "map", schema = "rpgmap")
public class Map implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;

	@Id
	private int id;
	@Column(name = "OWNER_ID")
	private int ownerId;
	private String name;

	public Map() {
	}

	public Map(int id) {
		setId(id);
	}

	public String toString() {
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append("Map ID: " + getId() + " Name: " + getName() + " Owner ID: " + getOwnerId());
		return outputBuffer.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
