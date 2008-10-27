package com.cyface.rpg.map.client;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapPoint;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.overlay.Marker;

public class RPGMapMarkerClickHandler implements MarkerClickHandler {

	MapWidget parentMapWidget;
	RPGMapMap parentRPGMapMap;
	RPGMapPoint parentRPGMapPoint;

	public RPGMapMarkerClickHandler(MapWidget parentMapWidget, RPGMapMap parentRPGMapMap, RPGMapPoint parentRPGMapPoint) {
		this.parentMapWidget = parentMapWidget;
		this.parentRPGMapMap = parentRPGMapMap;
		this.parentRPGMapPoint = parentRPGMapPoint;
	}
	
	public void onClick(MarkerClickEvent event) {
		Marker clickedMarker = event.getSender();
		if (clickedMarker != null && parentMapWidget != null && parentRPGMapMap != null && parentRPGMapPoint != null) {
			InfoWindowContent newInfoWindowContent = new InfoWindowContent(parentRPGMapPoint.getName());
			InfoWindow currentInfoWindow = parentMapWidget.getInfoWindow();
			currentInfoWindow.open(clickedMarker, newInfoWindowContent);
		}

	}

}
