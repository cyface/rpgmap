package com.cyface.rpg.map.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "attachment")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapAttachment.getAll", query = "SELECT attachment FROM RPGMapAttachment as attachment ORDER BY name")
public class RPGMapAttachment extends LazyPojo implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;

	public static final String MARKER_TYPE = "MARKER";

	private int id;
	private RPGMapMap parentRPGMapMap;
	private RPGMapOverlay parentRPGMapOverlay;
	private RPGMapUser parentRPGMapUser;
	private String name;
	private String attachment;
	private Date lastUpdated;

	public String toString() {
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append("Attachment ID: ");
		outputBuffer.append(getId());
		outputBuffer.append("\tName: ");
		outputBuffer.append(getName());
		outputBuffer.append("\tLast Updated: ");
		outputBuffer.append(getLastUpdated());
		if (getParentRPGMapMap() != null) {
			outputBuffer.append("\tParent Map ID/Name: ");
			outputBuffer.append(getParentRPGMapMap().getId());
			outputBuffer.append("/");
			outputBuffer.append(getParentRPGMapMap().getName());
		}
		if (getParentRPGMapOverlay() != null) {
			outputBuffer.append("\tParent Overlay ID/Name: ");
			outputBuffer.append(getParentRPGMapOverlay().getId());
			outputBuffer.append("/");
			outputBuffer.append(getParentRPGMapOverlay().getName());
		}
		if (getParentRPGMapUser() != null) {
			outputBuffer.append("\tParent User ID/Name: ");
			outputBuffer.append(getParentRPGMapUser().getId());
			outputBuffer.append("/");
			outputBuffer.append(getParentRPGMapUser().getName());
		}
		outputBuffer.append("\n");
		outputBuffer.append("Attachment: ");
		outputBuffer.append(getAttachment());

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

	@ManyToOne
	@JoinColumn(name = "parent_overlay_id")
	public RPGMapOverlay getParentRPGMapOverlay() {
		return parentRPGMapOverlay;
	}

	@ManyToOne
	@JoinColumn(name = "parent_overlay_id")
	public void setParentRPGMapOverlay(RPGMapOverlay parentRPGMapOverlay) {
		this.parentRPGMapOverlay = parentRPGMapOverlay;
	}

	@ManyToOne
	@JoinColumn(name = "parent_user_id")
	public RPGMapUser getParentRPGMapUser() {
		return parentRPGMapUser;
	}

	@ManyToOne
	@JoinColumn(name = "parent_user_id")
	public void setParentRPGMapUser(RPGMapUser parentRPGMapUser) {
		this.parentRPGMapUser = parentRPGMapUser;
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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

	
}
