package com.cyface.rpg.map.client.handlers;

import com.cyface.rpg.map.client.contextmenu.RPGMapContextMenu;
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
