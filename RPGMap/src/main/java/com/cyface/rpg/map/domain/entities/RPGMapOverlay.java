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

@Entity
@Table(name = "overlay")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapOverlay.getAll", query = "SELECT overlay FROM RPGMapOverlay as overlay ORDER BY name")
public class RPGMapOverlay extends LazyPojo implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;

	public static final String MARKER_TYPE = "MARKER";

	private int id;
	private RPGMapMap parentRPGMapMap;
	private Set<RPGMapAttachment> childRPGMapAttachments;
	private double latitude;
	private double longitude;
	private String name;
	private String type;
	private Date lastUpdated;

	public String toString() {
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append("Overlay ID: ");
		outputBuffer.append(getId());
		outputBuffer.append("\tName: ");
		outputBuffer.append(getName());
		outputBuffer.append("\tType: ");
		outputBuffer.append(getType());
		outputBuffer.append("\tLat/Lng: ");
		outputBuffer.append(getLatitude());
		outputBuffer.append("/");
		outputBuffer.append(getLongitude());
		outputBuffer.append("\tLast Updated: ");
		outputBuffer.append(getLastUpdated());
		outputBuffer.append("\tParent Map ID/Name: ");
		outputBuffer.append(getParentRPGMapMap().getId());
		outputBuffer.append("/");
		outputBuffer.append(getParentRPGMapMap().getName());
		if (getChildRPGMapAttachments() != null) {
			outputBuffer.append("\nOverlay Attachments: ");
			outputBuffer.append("(" + getChildRPGMapAttachments().size() + ")\n");
			Iterator<RPGMapAttachment> childAttachmentsIterator = getChildRPGMapAttachments().iterator();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@ManyToOne
	@JoinColumn(name = "parent_map_id")
	public RPGMapMap getParentRPGMapMap() {
		return parentRPGMapMap;
	}

	@ManyToOne
	@JoinColumn(name = "parent_map_id")
	public void setParentRPGMapMap(RPGMapMap parentRPGMapMap) {
		this.parentRPGMapMap = parentRPGMapMap;
	}

	@OneToMany(mappedBy = "parentRPGMapOverlay", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "parent_overlay_id")
	public Set<RPGMapAttachment> getChildRPGMapAttachments() {
		return childRPGMapAttachments;
	}

	@OneToMany(mappedBy = "parentRPGMapOverlay", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "parent_overlay_id")
	public void setChildRPGMapAttachments(Set<RPGMapAttachment> childAttachments) {
		this.childRPGMapAttachments = childAttachments;
	}
	
	public void addChildAttachment(RPGMapAttachment attachmentToAdd) {
		attachmentToAdd.setParentRPGMapOverlay(this);
		if (this.childRPGMapAttachments == null) {
			this.childRPGMapAttachments = new HashSet<RPGMapAttachment>();
		}
		this.childRPGMapAttachments.add(attachmentToAdd);
	}
}
