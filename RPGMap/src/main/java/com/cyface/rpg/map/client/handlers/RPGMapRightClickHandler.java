package com.cyface.rpg.map.client.handlers;

import com.cyface.rpg.map.client.contextmenu.RPGMapContextMenu;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;

public class RPGMapRightClickHandler implements MapRightClickHandler {

	MapWidget parentMapWidget;

	public RPGMapRightClickHandler(MapWidget parentMap) {
		this.parentMapWidget = parentMap;
	}

	public void onRightClick(MapRightClickEvent event) {
		Overlay clickedOverlay = event.getOverlay();
		Point clickedPoint = event.getPoint();
		
		RPGMapContextMenu.hide();
		
		/* If we don't have a valid parent map, exit */
		if (parentMapWidget == null) {
			return;
		}
		
		/* Check to see if they right-clicked on a Marker */
		if (clickedOverlay != null && clickedOverlay instanceof Marker){
			Marker clickedMarker = (Marker) clickedOverlay;
			RPGMapContextMenu.openMarkerMenu(parentMapWidget, clickedPoint, clickedMarker);
			return;
		}
		
		/* Otherwise, show the add menu */
		if (clickedOverlay != null && clickedPoint != null){
			RPGMapContextMenu.openEmptySpaceMenu(parentMapWidget, clickedPoint);
		}

	}

}
