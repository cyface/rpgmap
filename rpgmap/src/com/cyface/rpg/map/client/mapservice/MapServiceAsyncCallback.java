package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;
import java.util.Iterator;

import com.cyface.rpg.map.client.RPGMapMarkerClickHandler;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapPoint;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MapServiceAsyncCallback implements AsyncCallback<ArrayList<RPGMapMap>> {

	MapWidget parentMapWidget;

	public MapServiceAsyncCallback(MapWidget parentMap) {
		this.parentMapWidget = parentMap;
	}

	public void onFailure(Throwable error) {
		Window.alert("Fail!");

	}

	public void onSuccess(ArrayList<RPGMapMap> rpgMapList) {
		if (this.parentMapWidget != null && rpgMapList != null) {
			Iterator<RPGMapMap> rpgMapListIterator = rpgMapList.iterator();
			while (rpgMapListIterator.hasNext()) {
				RPGMapMap currentMap = rpgMapListIterator.next();
				if (currentMap.getChildRPGMapPoints() != null) {
					Iterator<RPGMapPoint> childPointsIterator = currentMap.getChildRPGMapPoints().iterator();
					while (childPointsIterator.hasNext()) {
						RPGMapPoint currentPoint = childPointsIterator.next();
						MarkerOptions newMarkerOptions = MarkerOptions.newInstance();
						newMarkerOptions.setTitle(currentPoint.getName());
						Marker newMarker = new Marker(LatLng.newInstance(currentPoint.getLatitude(), currentPoint.getLongitude()), newMarkerOptions);
						newMarker.addMarkerClickHandler(new RPGMapMarkerClickHandler(parentMapWidget, currentMap, currentPoint));
						parentMapWidget.addOverlay(newMarker);
					}
				}
			}
		}

	}
}
