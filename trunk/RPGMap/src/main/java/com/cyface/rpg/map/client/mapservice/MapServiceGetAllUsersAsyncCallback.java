package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;

import com.cyface.rpg.map.client.mapmanager.RPGMapManager;
import com.cyface.rpg.map.domain.entities.RPGMapUser;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MapServiceGetAllUsersAsyncCallback implements AsyncCallback<ArrayList<RPGMapUser>> {

	MapWidget parentMapWidget;

	public MapServiceGetAllUsersAsyncCallback(MapWidget parentMap) {
		this.parentMapWidget = parentMap;
	}

	public void onFailure(Throwable error) {
		Window.alert("Attempt to get list of maps from the database has failed!");
	}

	public void onSuccess(ArrayList<RPGMapUser> userList) {
		RPGMapManager.setUserList(userList);
	}
}
