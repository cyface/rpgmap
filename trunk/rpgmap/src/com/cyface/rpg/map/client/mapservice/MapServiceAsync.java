package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface MapServiceAsync {
	public void getAllMaps(AsyncCallback<ArrayList<RPGMapMap>> callback);
	
	public void saveMap(RPGMapMap mapToSave, AsyncCallback<RPGMapMap> callback);
}
