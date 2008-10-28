package com.cyface.rpg.map.client.contextmenu;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Command;

public class RemoveMarkerCommand implements Command {

	MapWidget parentMapWidget;
	Marker clickedMarker;
	
	public RemoveMarkerCommand(MapWidget parentMapWidget, Marker clickedMarker) {
		this.parentMapWidget = parentMapWidget;
		this.clickedMarker = clickedMarker;
	}
	
	public void execute() {
		RPGMapContextMenu.hide();
		parentMapWidget.removeOverlay(clickedMarker);
	}

}
