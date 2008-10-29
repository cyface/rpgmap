package com.cyface.rpg.map.client.handlers;

import com.cyface.rpg.map.client.contextmenu.RPGMapContextMenu;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;

public class RPGMapClickHandler implements MapClickHandler {

	MapWidget parentMapWidget;

	public RPGMapClickHandler(MapWidget parentMap) {
		this.parentMapWidget = parentMap;
	}

	public void onClick(MapClickEvent event) {
		RPGMapContextMenu.hide();
	}

}
