package com.cyface.rpg.map.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapDragHandler;

public class RPGMapDragHandler implements MapDragHandler {

	MapWidget parentMapWidget;

	public RPGMapDragHandler(MapWidget parentMap) {
		this.parentMapWidget = parentMap;
	}

	public void onDrag(MapDragEvent event) {		
		RPGMapContextMenu.hide();
	}

}
