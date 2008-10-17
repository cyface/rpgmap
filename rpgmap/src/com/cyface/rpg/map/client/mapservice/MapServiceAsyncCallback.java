package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;

import com.cyface.rpg.map.client.entities.RPGMapMap;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MapServiceAsyncCallback implements AsyncCallback<ArrayList<RPGMapMap>> {

	public void onFailure(Throwable error) {
		Window.alert("Fail!");
		
	}

	public void onSuccess(ArrayList<RPGMapMap> rpgMapList) {
		Window.alert(rpgMapList.size() + "Size");
		
	}

}
