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

@Entity
@Table(name = "user")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapUser.getAll", query = "SELECT map FROM RPGMapUser as map ORDER BY userId")
public class RPGMapUser extends LazyPojo implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;
	private int id;
	private String username;
	private String name;
	private String password;
	private boolean enabled = true;
	private Set<RPGMapMap> childRPGMapMaps;
	private Set<RPGMapAttachment> childRPGMapAttachments;
	private Date lastUpdated;

	public RPGMapUser() {
	}

	public RPGMapUser(int id) {
		setId(id);
	}

	public String toString() {
		StringBuffer outputBuffer = new StringBuffer();
		outputBuffer.append("\nRecord ID: ");
		outputBuffer.append(getId());
		outputBuffer.append("\tUser ID: ");
		outputBuffer.append(getUsername());
		outputBuffer.append("\tName: ");
		outputBuffer.append(getName());
		outputBuffer.append("\tPassword: ");
		outputBuffer.append(getPassword());
		outputBuffer.append("\tLast Updated: ");
		outputBuffer.append(getLastUpdated());
		if (getChildRPGMapMaps() != null) {
			outputBuffer.append("\nMaps: ");
			outputBuffer.append("(" + getChildRPGMapMaps().size() + ")\n");
			Iterator<RPGMapMap> childMapsIterator = getChildRPGMapMaps().iterator();
			while (childMapsIterator.hasNext()) {
				RPGMapMap currentRPGMapMap = childMapsIterator.next();
				outputBuffer.append(currentRPGMapMap);
			}
		}
		if (getChildRPGMapAttachments() != null) {
			outputBuffer.append("\nUser Attachments: ");
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
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

	@OneToMany(mappedBy = "parentRPGMapUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<RPGMapMap> getChildRPGMapMaps() {
		return childRPGMapMaps;
	}

	@OneToMany(mappedBy = "parentRPGMapUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public void setChildRPGMapMaps(Set<RPGMapMap> childRPGMapMaps) {
		this.childRPGMapMaps = childRPGMapMaps;
	}

	public void addChildRPGMapMap(RPGMapMap mapToAdd) {
		mapToAdd.setParentRPGMapUser(this);
		if (this.childRPGMapMaps == null) {
			this.childRPGMapMaps = new HashSet<RPGMapMap>();
		}
		this.childRPGMapMaps.add(mapToAdd);
	}

	@OneToMany(mappedBy = "parentRPGMapUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "parent_user_id")
	public Set<RPGMapAttachment> getChildRPGMapAttachments() {
		return childRPGMapAttachments;
	}

	@OneToMany(mappedBy = "parentRPGMapUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column(name = "parent_user_id")
	public void setChildRPGMapAttachments(Set<RPGMapAttachment> childARPGMapttachments) {
		this.childRPGMapAttachments = childARPGMapttachments;
	}
	
	public void addChildAttachment(RPGMapAttachment attachmentToAdd) {
		attachmentToAdd.setParentRPGMapUser(this);
		if (this.childRPGMapAttachments == null) {
			this.childRPGMapAttachments = new HashSet<RPGMapAttachment>();
		}
		this.childRPGMapAttachments.add(attachmentToAdd);
	}

}
