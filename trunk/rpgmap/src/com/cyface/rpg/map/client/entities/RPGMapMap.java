package com.cyface.rpg.map.client.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

@Entity
@Table(name = "map", schema = "rpgmap")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapMap.getAll", query = "SELECT map FROM RPGMapMap as map ORDER BY name")
public class RPGMapMap implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;
	private int id;
	private int ownerId;
	private String name;
	private Set<RPGMapPoint> childRPGMapPoints;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "OWNER_ID")
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

	@OneToMany(mappedBy = "parentRPGMapMap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<RPGMapPoint> getChildRPGMapPoints() {
		return childRPGMapPoints;
	}

	public void setChildRPGMapPoints(Set<RPGMapPoint> childRPGMapPoints) {
		this.childRPGMapPoints = childRPGMapPoints;
	}
}
