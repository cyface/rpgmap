package com.cyface.rpg.map.client.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

@Entity
@Table(name="map", schema="rpgmap")
@PersistenceUnit(name="rpgmap")
@NamedQuery(name="RPGMapMap.getAll", query="SELECT map FROM RPGMapMap as map ORDER BY name")
public class RPGMapMap implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;

	@Id
	private int id;
	@Column(name = "OWNER_ID")
	private int ownerId;
	private String name;

	public RPGMapMap() {
	}

	public RPGMapMap(int id) {
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
