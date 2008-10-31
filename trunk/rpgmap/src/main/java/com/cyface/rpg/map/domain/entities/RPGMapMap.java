package com.cyface.rpg.map.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

import com.google.gwt.maps.client.overlay.Marker;

@Entity
@Table(name = "map", schema = "rpgmap")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapMap.getAll", query = "SELECT map FROM RPGMapMap as map ORDER BY name")
public class RPGMapMap extends LazyPojo implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;
	private int id;
	private int ownerId;
	private String name;
	private Set<RPGMapOverlay> childRPGMapOverlays;
	private Date lastUpdated;

	public RPGMapMap() {
	}

	public RPGMapMap(int id) {
		setId(id);
	}

	public String toString() {
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append("\nMap ID: ");
		outputBuffer.append(getId());
		outputBuffer.append("\tName: ");
		outputBuffer.append(getName());
		outputBuffer.append("\tOwner ID: ");
		outputBuffer.append(getOwnerId());
		outputBuffer.append("\tLast Updated: ");
		outputBuffer.append(getLastUpdated());
		outputBuffer.append("\nOverlays: ");
		outputBuffer.append("(" + getChildRPGMapOverlays().size() + ")\n" );
		Iterator<RPGMapOverlay> childOverlaysIterator = getChildRPGMapOverlays().iterator();
		while (childOverlaysIterator.hasNext()) {
			RPGMapOverlay currentRPGMapOverlay = childOverlaysIterator.next();
			outputBuffer.append(currentRPGMapOverlay);
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

	@Column(name = "LAST_UPDATED")
	public Date getLastUpdated() {
		return lastUpdated;
	}

	@SuppressWarnings("unused")
	@Column(name = "LAST_UPDATED")
	private void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@OneToMany(mappedBy = "parentRPGMapMap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column (name = "parent_map_id")
	public Set<RPGMapOverlay> getChildRPGMapOverlays() {
		return childRPGMapOverlays;
	}

	@OneToMany(mappedBy = "parentRPGMapMap", cascade = CascadeType.ALL)
	@Column (name = "parent_map_id")
	public void setChildRPGMapOverlays(Set<RPGMapOverlay> childRPGMapOverlays) {
		this.childRPGMapOverlays = childRPGMapOverlays;
	}

	public void addChildRPGMapOverlay(RPGMapOverlay pointToAdd) {
		pointToAdd.setParentRPGMapMap(this);
		if (this.childRPGMapOverlays == null) {
			this.childRPGMapOverlays = new HashSet<RPGMapOverlay>();
		}
		this.childRPGMapOverlays.add(pointToAdd);
	}

	public void addChildMarker(Marker markerToAdd) {
		RPGMapOverlay newMarker = new RPGMapOverlay();
		newMarker.setLatitude(markerToAdd.getLatLng().getLatitude());
		newMarker.setLongitude(markerToAdd.getLatLng().getLongitude());
		newMarker.setType(RPGMapOverlay.MARKER_TYPE);
		if (markerToAdd.getTitle() != null) {
			newMarker.setName(markerToAdd.getTitle());
		} else {
			newMarker.setName("No Name Yet");
		}
		this.addChildRPGMapOverlay(newMarker);
	}
}
