package com.cyface.rpg.map.server;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "map", schema="rpgmap")
public class Map implements Serializable {
	private static final long serialVersionUID = -7201186159122319296L;

	@Id
	private int id;
	private int owner_id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOwnerId() {
		return owner_id;
	}
	public void setOwnerId(int ownerId) {
		this.owner_id = ownerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
