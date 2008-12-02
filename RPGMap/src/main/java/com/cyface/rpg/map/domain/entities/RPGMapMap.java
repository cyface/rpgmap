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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

import net.sf.hibernate4gwt.pojo.java5.LazyPojo;

import com.google.gwt.maps.client.overlay.Marker;

@Entity
@Table(name = "map")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapMap.getAll", query = "SELECT map FROM RPGMapMap as map ORDER BY name")
public class RPGMapMap extends LazyPojo implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;
	private int id;
	private String name;
	private boolean publicallyViewable = true;
	private RPGMapUser parentRPGMapUser;
	private Set<RPGMapOverlay> childRPGMapOverlays;
	private Set<RPGMapAttachment> childRPGMapAttachments;
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
		outputBuffer.append("\tOwner: ");
		outputBuffer.append(getParentRPGMapUser().getId() + "/" + getParentRPGMapUser().getUsername() + "/" + getParentRPGMapUser().getName());
		outputBuffer.append("\tLast Updated: ");
		outputBuffer.append(getLastUpdated());
		if (getChildRPGMapOverlays() != null) {
			outputBuffer.append("\nOverlays: ");
			outputBuffer.append("(" + getChildRPGMapOverlays().size() + ")\n");
			Iterator<RPGMapOverlay> childOverlaysIterator = getChildRPGMapOverlays().iterator();
			while (childOverlaysIterator.hasNext()) {
				RPGMapOverlay currentRPGMapOverlay = childOverlaysIterator.next();
				outputBuffer.append(currentRPGMapOverlay);
			}
		}
		if (getChildAttachments() != null) {
			outputBuffer.append("\nMap Attachments: ");
			outputBuffer.append("(" + getChildAttachments().size() + ")\n");
			Iterator<RPGMapAttachment> childAttachmentsIterator = getChildAttachments().iterator();
			while (childAttachmentsIterator.hasNext()) {
				RPGMapAttachment currentRPGMapAttachment = childAttachmentsIterator.next();
				outputBuffer.append(currentRPGMapAttachment);
			}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "public")
	public boolean isPublicallyViewable() {
		return publicallyViewable;
	}

	@Column(name = "public")
	public void setPublicallyViewable(boolean publicallyViewable) {
		this.publicallyViewable = publicallyViewable;
	}

	@ManyToOne
	@JoinColumn(name = "parent_user_id")
	public void setParentRPGMapUser(RPGMapUser parentRPGMapUser) {
		this.parentRPGMapUser = parentRPGMapUser;
	}

	@ManyToOne
	@JoinColumn(name = "parent_user_id")
	public RPGMapUser getParentRPGMapUser() {
		return parentRPGMapUser;
	}

	@Column(name = "last_updated")
	public Date getLastUpdated() {
		return lastUpdated;
	}

	@SuppressWarnings("unused")
	@Column(name = "last_updated")
	private void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@OneToMany(mappedBy = "parentRPGMapMap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "parent_map_id")
	public Set<RPGMapOverlay> getChildRPGMapOverlays() {
		return childRPGMapOverlays;
	}

	@OneToMany(mappedBy = "parentRPGMapMap", cascade = CascadeType.ALL)
	@Column(name = "parent_map_id")
	public void setChildRPGMapOverlays(Set<RPGMapOverlay> childRPGMapOverlays) {
		this.childRPGMapOverlays = childRPGMapOverlays;
	}

	public void addChildRPGMapOverlay(RPGMapOverlay overlayToAdd) {
		overlayToAdd.setParentRPGMapMap(this);
		if (this.childRPGMapOverlays == null) {
			this.childRPGMapOverlays = new HashSet<RPGMapOverlay>();
		}
		this.childRPGMapOverlays.add(overlayToAdd);
	}

	public RPGMapOverlay addChildMarker(Marker markerToAdd) {
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
		return newMarker;
	}

	@OneToMany(mappedBy = "parentRPGMapMap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "parent_map_id")
	public Set<RPGMapAttachment> getChildAttachments() {
		return childRPGMapAttachments;
	}

	@OneToMany(mappedBy = "parentRPGMapMap", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "parent_map_id")
	public void setChildAttachments(Set<RPGMapAttachment> childRPGMapAttachments) {
		this.childRPGMapAttachments = childRPGMapAttachments;
	}

	public void addChildAttachment(RPGMapAttachment attachmentToAdd) {
		attachmentToAdd.setParentRPGMapMap(this);
		if (this.childRPGMapAttachments == null) {
			this.childRPGMapAttachments = new HashSet<RPGMapAttachment>();
		}
		this.childRPGMapAttachments.add(attachmentToAdd);
	}
}
