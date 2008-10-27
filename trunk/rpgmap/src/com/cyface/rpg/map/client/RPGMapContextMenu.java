package com.cyface.rpg.map.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;

public class RPGMapContextMenu {
	
	private static RPGMapContextMenu instance;
	
	PopupPanel contextMenuPanel;
	MenuBar contextMenuBar;
	MenuItem addMarkerMenuItem;

	protected RPGMapContextMenu() {
		contextMenuPanel = new PopupPanel();
	}

	public static RPGMapContextMenu getInstance() {
		if (instance == null) {
			instance = new RPGMapContextMenu();
		}
		return instance;
	}
	
	public static void show(MapWidget parentMapWidget, Point clickedPoint) {
		RPGMapContextMenu menu = RPGMapContextMenu.getInstance();
		LatLng clickedLatLng = parentMapWidget.convertDivPixelToLatLng(clickedPoint);
		menu.addMarkerMenuItem = new MenuItem("Add New Marker Here", new AddMarkerCommand(parentMapWidget, clickedLatLng));
		menu.contextMenuPanel.setPopupPosition(clickedPoint.getX(), clickedPoint.getY());
		menu.contextMenuPanel.show();
	}
	
	public static void hide() {
		RPGMapContextMenu menu = RPGMapContextMenu.getInstance();
		menu.contextMenuPanel.hide();
	}

}
