package com.cyface.rpg.map.client.contextmenu;

import com.cyface.rpg.map.client.mapmanager.RPGMapManager;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.Command;

public class SaveAllCommand implements Command {

	MapWidget parentMapWidget;
	RPGMapMap parentRPGMapMap;
	
	public SaveAllCommand(MapWidget parentMapWidget, RPGMapMap parentRPGMapMap) {
		this.parentMapWidget = parentMapWidget;
		this.parentRPGMapMap = parentRPGMapMap;
	}
	
	public void execute() {
		RPGMapContextMenu.hide();
		RPGMapManager.saveMap(parentRPGMapMap);
	}

}
