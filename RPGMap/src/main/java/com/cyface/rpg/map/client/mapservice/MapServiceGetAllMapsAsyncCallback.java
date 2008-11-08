package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;
import java.util.Iterator;


import com.cyface.rpg.map.client.mapmanager.RPGMapManager;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MapServiceGetAllMapsAsyncCallback implements AsyncCallback<ArrayList<RPGMapMap>> {

	MapWidget parentMapWidget;

	public MapServiceGetAllMapsAsyncCallback(MapWidget parentMap) {
		this.parentMapWidget = parentMap;
	}

	public void onFailure(Throwable error) {
		Window.alert("Attempt to get list of maps from the database has failed!");
	}

	public void onSuccess(ArrayList<RPGMapMap> rpgMapList) {
		RPGMapManager.setRpgMapList(rpgMapList);
		Iterator<RPGMapMap> mapListIterator = RPGMapManager.getRpgMapList().iterator();
		while(mapListIterator.hasNext()) {
			RPGMapMap currentMap = mapListIterator.next();
			RPGMapManager.plotMapItems(currentMap);
		}
	}
}
