package com.cyface.rpg.map.client.contextmenu;

import java.util.Iterator;

import com.cyface.rpg.map.client.mapmanager.RPGMapManager;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

public class RPGMapContextMenu {
	
	private static RPGMapContextMenu instance;
	
	PopupPanel contextMenuPanel;

	protected RPGMapContextMenu() {
		contextMenuPanel = new PopupPanel();
	}

	public static RPGMapContextMenu getInstance() {
		if (instance == null) {
			instance = new RPGMapContextMenu();
		}
		return instance;
	}
	
	public static void openEmptySpaceMenu(MapWidget parentMapWidget, Point clickedPoint) {
		RPGMapContextMenu menu = RPGMapContextMenu.getInstance();
		menu.contextMenuPanel.hide();
		menu.contextMenuPanel = new PopupPanel();
		
		MenuBar contextMenu = new MenuBar(true);
		
		/* Generate list of maps for the add command */
		MenuBar mapListAddMarkerMenu = new MenuBar(true);
		Iterator<RPGMapMap> mapListAddIterator = RPGMapManager.getRpgMapList().iterator();
		while(mapListAddIterator.hasNext()) {
			RPGMapMap currentMap = mapListAddIterator.next();
			MenuItem newMapMenuItem = new MenuItem(currentMap.getName(), new AddMarkerCommand(parentMapWidget, clickedPoint, currentMap));
			mapListAddMarkerMenu.addItem(newMapMenuItem);
		}
		
		MenuItem addMarkerMenuItem = new MenuItem("Add New Marker Here To", mapListAddMarkerMenu);
		
		/* Generate list of maps for the save command */
		MenuBar mapListSaveMarkerMenu = new MenuBar(true);
		Iterator<RPGMapMap> mapListSaveIterator = RPGMapManager.getRpgMapList().iterator();
		while(mapListSaveIterator.hasNext()) {
			RPGMapMap currentMap = mapListSaveIterator.next();
			MenuItem newMapMenuItem = new MenuItem(currentMap.getName(), new SaveAllCommand(parentMapWidget, currentMap));
			mapListSaveMarkerMenu.addItem(newMapMenuItem);
		}
		
		MenuItem addSaveAllMenuItem = new MenuItem("Save All Markers For",  mapListSaveMarkerMenu);
		contextMenu.addItem(addMarkerMenuItem);
		contextMenu.addItem(addSaveAllMenuItem);
		menu.contextMenuPanel.add(contextMenu);
		
		menu.contextMenuPanel.setPopupPosition(clickedPoint.getX(), clickedPoint.getY());
		menu.contextMenuPanel.show();
	}
	
	public static void openMarkerMenu(MapWidget parentMapWidget, Point clickedPoint, Marker clickedMarker) {
		RPGMapManager manager = RPGMapManager.getInstance();
		RPGMapOverlay clickedRPGMapOverlay = manager.getOverlayMap().get(clickedMarker);
		if (clickedRPGMapOverlay == null) {
			Window.alert("Clicked Marker Has No Matching Overlay Object!");
		}
		
		
		RPGMapContextMenu menu = RPGMapContextMenu.getInstance();
		menu.contextMenuPanel.hide();
		menu.contextMenuPanel = new PopupPanel();
		
		MenuBar contextMenu = new MenuBar(true);
		MenuItem renameMarkerMenuItem = new MenuItem("Edit This Marker", new EditMarkerCommand(parentMapWidget, clickedMarker, clickedRPGMapOverlay));
		contextMenu.addItem(renameMarkerMenuItem);
		MenuItem addMarkerMenuItem = new MenuItem("Remove This Marker", new RemoveMarkerCommand(parentMapWidget, clickedMarker, clickedRPGMapOverlay));
		contextMenu.addItem(addMarkerMenuItem);
		menu.contextMenuPanel.add(contextMenu);
		
		menu.contextMenuPanel.setPopupPosition(clickedPoint.getX(), clickedPoint.getY());
		menu.contextMenuPanel.show();
	}
	
	public static void hide() {
		RPGMapContextMenu menu = RPGMapContextMenu.getInstance();
		menu.contextMenuPanel.hide();
	}

}
