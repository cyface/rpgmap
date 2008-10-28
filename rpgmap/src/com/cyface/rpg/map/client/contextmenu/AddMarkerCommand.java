package com.cyface.rpg.map.client.contextmenu;

import com.cyface.rpg.map.client.mapmanager.RPGMapManager;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Command;

public class AddMarkerCommand implements Command {

	MapWidget parentMapWidget;
	Point clickedPoint;
	RPGMapMap parentRPGMap;
	
	public AddMarkerCommand(MapWidget parentMapWidget, Point clickedPoint, RPGMapMap parentRPGMap) {
		this.parentMapWidget = parentMapWidget;
		this.clickedPoint = clickedPoint;
		this.parentRPGMap = parentRPGMap;
	}
	
	public void execute() {
		RPGMapContextMenu.hide();
		LatLng clickedLatLng = parentMapWidget.convertDivPixelToLatLng(clickedPoint);
		Marker newMarker = new Marker(clickedLatLng);
		RPGMapManager.addOverlay(this.parentRPGMap, newMarker);
	}

}
