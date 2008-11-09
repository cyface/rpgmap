package com.cyface.rpg.map.client.contextmenu;

import com.cyface.rpg.map.client.editors.MarkerEditor;
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Command;

public class EditMarkerCommand implements Command {

	MapWidget parentMapWidget;
	Marker clickedMarker;
	RPGMapOverlay clickedRPGMapOverlay;

	public EditMarkerCommand(MapWidget parentMapWidget, Marker clickedMarker, RPGMapOverlay clickedRPGMapOverlay) {
		this.parentMapWidget = parentMapWidget;
		this.clickedMarker = clickedMarker;
		this.clickedRPGMapOverlay = clickedRPGMapOverlay;
	}

	public void execute() {
		RPGMapContextMenu.hide();
		MarkerEditor markerEditor = new MarkerEditor(parentMapWidget, clickedMarker, clickedRPGMapOverlay);
		markerEditor.editMarker();
	}

}
