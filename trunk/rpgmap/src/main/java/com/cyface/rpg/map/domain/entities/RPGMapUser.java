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
@Table(name = "user", schema = "rpgmap")
@PersistenceUnit(name = "rpgmap")
@NamedQuery(name = "RPGMapUser.getAll", query = "SELECT map FROM RPGMapUser as map ORDER BY userId")
public class RPGMapUser extends LazyPojo implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;
	private int id;
	private String userId;
	private String name;
	private String password;
	private Set<RPGMapMap> childRPGMapMaps;
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
		outputBuffer.append(getUserId());
		outputBuffer.append("\tName: ");
		outputBuffer.append(getName());
		outputBuffer.append("\tPassword: ");
		outputBuffer.append(getPassword());
		outputBuffer.append("\tLast Updated: ");
		outputBuffer.append(getLastUpdated());
		outputBuffer.append("\nOverlays: ");
		outputBuffer.append("(" + getChildRPGMapMaps().size() + ")\n" );
		Iterator<RPGMapMap> childMapsIterator = getChildRPGMapMaps().iterator();
		while (childMapsIterator.hasNext()) {
			RPGMapMap currentRPGMapMap = childMapsIterator.next();
			outputBuffer.append(currentRPGMapMap);
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

	@Column(name = "user_id")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
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

	@OneToMany(mappedBy = "parentRPGMapUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column (name = "owner_id")
	public Set<RPGMapMap> getChildRPGMapMaps() {
		return childRPGMapMaps;
	}

	@OneToMany(mappedBy = "parentRPGMapUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Column (name = "owner_id")
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

}
