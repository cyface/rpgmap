package com.cyface.rpg.map.domain.entities;

import java.io.Serializable;
import java.util.Iterator;
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

import net.sf.hibernate4gwt.pojo.java5.LazyPojo;


@Entity
@Table(name = "map", schema = "rpgmap")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapMap.getAll", query = "SELECT map FROM RPGMapMap as map ORDER BY name")
public class RPGMapMap extends LazyPojo implements Serializable {
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
		outputBuffer.append("Map ID: ");
		outputBuffer.append(getId());
		outputBuffer.append("\tName: ");
		outputBuffer.append(getName()); 
		outputBuffer.append("\tOwner ID: ");
		outputBuffer.append(getOwnerId());
		outputBuffer.append("\nPoints: \n");
		Iterator<RPGMapPoint> childPointsIterator = getChildRPGMapPoints().iterator();
		while (childPointsIterator.hasNext()) {
			RPGMapPoint currentRPGMapPoint = childPointsIterator.next();
			outputBuffer.append("\tName: ");
			outputBuffer.append(currentRPGMapPoint.getName());
			outputBuffer.append("\tLat/Lng: ");
			outputBuffer.append(currentRPGMapPoint.getLatitude());
			outputBuffer.append("/");
			outputBuffer.append(currentRPGMapPoint.getLongitude());
			outputBuffer.append("\n");
		}
		
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
