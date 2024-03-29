package com.cyface.rpg.map.client.contextmenu;

import com.cyface.rpg.map.client.mapmanager.RPGMapManager;
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Command;

public class RemoveMarkerCommand implements Command {

	MapWidget parentMapWidget;
	Marker clickedMarker;
	RPGMapOverlay rpgMapOverlayToSave;
	
	public RemoveMarkerCommand(MapWidget parentMapWidget, Marker clickedMarker, RPGMapOverlay rpgMapOverlayToSave) {
		this.parentMapWidget = parentMapWidget;
		this.clickedMarker = clickedMarker;
		this.rpgMapOverlayToSave = rpgMapOverlayToSave;
	}
	
	public void execute() {
		RPGMapContextMenu.hide();
		RPGMapManager.deleteOverlay(clickedMarker);
	}

}
