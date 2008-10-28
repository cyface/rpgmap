package com.cyface.rpg.map.client.mapservice;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MapServiceSaveMapAsyncCallback implements AsyncCallback<RPGMapMap> {

	public void onFailure(Throwable arg0) {
		Window.alert("Save Failed!");

	}

	public void onSuccess(RPGMapMap arg0) {
		Window.alert("Save Successful");
		
	}

}
