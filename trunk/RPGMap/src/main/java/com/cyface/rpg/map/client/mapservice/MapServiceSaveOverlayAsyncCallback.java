package com.cyface.rpg.map.client.mapservice;

import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MapServiceSaveOverlayAsyncCallback implements AsyncCallback<RPGMapOverlay> {

	public void onFailure(Throwable arg0) {
		Window.alert("Save Failed!");

	}

	public void onSuccess(RPGMapOverlay arg0) {
		Window.alert("Save Successful");
		
	}

}
