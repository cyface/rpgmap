package com.cyface.rpg.map.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Command;

public class AddMarkerCommand implements Command {

	MapWidget parentMapWidget;
	LatLng clickedLatLng;
	
	public AddMarkerCommand(MapWidget parentMapWidget, LatLng clickedLatLng) {
		this.parentMapWidget = parentMapWidget;
		this.clickedLatLng = clickedLatLng;
	}
	
	public void execute() {
		RPGMapContextMenu.hide();
		Marker newMarker = new Marker(clickedLatLng);
		parentMapWidget.addOverlay(newMarker);
	}

}
