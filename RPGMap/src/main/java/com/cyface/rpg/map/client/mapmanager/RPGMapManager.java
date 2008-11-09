package com.cyface.rpg.map.client.mapmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.cyface.rpg.map.client.handlers.RPGMapMarkerClickHandler;
import com.cyface.rpg.map.client.mapservice.MapService;
import com.cyface.rpg.map.client.mapservice.MapServiceAsync;
import com.cyface.rpg.map.client.mapservice.MapServiceDeleteOverlayAsyncCallback;
import com.cyface.rpg.map.client.mapservice.MapServiceGetAllPublicMapsAsyncCallback;
import com.cyface.rpg.map.client.mapservice.MapServiceSaveMapAsyncCallback;
import com.cyface.rpg.map.client.mapservice.MapServiceSaveOverlayAsyncCallback;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.cyface.rpg.map.domain.entities.RPGMapUser;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class RPGMapManager {

	private static RPGMapManager instance;

	private ArrayList<RPGMapMap> rpgMapList;
	private ArrayList<RPGMapUser> userList;
	private HashMap<RPGMapMap, ArrayList<Overlay>> overlayList;
	private HashMap<Overlay, RPGMapOverlay> overlayMap;
	private MapWidget parentMapWidget;
	MapServiceAsync service;

	protected RPGMapManager() {

	}

	public static RPGMapManager getInstance() {
		if (instance == null) {
			instance = new RPGMapManager();
		}
		return instance;
	}

	public static void init(MapWidget parentMapWidget) {
		RPGMapManager manager = RPGMapManager.getInstance();
		manager.parentMapWidget = parentMapWidget;
		manager.overlayList = new HashMap<RPGMapMap, ArrayList<Overlay>>();
		manager.overlayMap = new HashMap<Overlay, RPGMapOverlay>();

		manager.service = (MapServiceAsync) GWT.create(MapService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) manager.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "mapservice");

		MapServiceGetAllPublicMapsAsyncCallback callback = new MapServiceGetAllPublicMapsAsyncCallback(parentMapWidget);

		manager.service.getAllPublicMaps(callback);
	}

	public static void plotMapItems(RPGMapMap mapToPlot) {
		RPGMapManager manager = RPGMapManager.getInstance();
		Set<RPGMapOverlay> childMapPoints = mapToPlot.getChildRPGMapOverlays();

		if (manager.parentMapWidget != null && mapToPlot != null && childMapPoints != null) {
			ArrayList<Overlay> childOverlaysList = new ArrayList<Overlay>();
			Iterator<RPGMapOverlay> childOverlaysIterator = childMapPoints.iterator();
			while (childOverlaysIterator.hasNext()) {
				RPGMapOverlay currentOverlay = childOverlaysIterator.next();
				MarkerOptions newMarkerOptions = MarkerOptions.newInstance();
				newMarkerOptions.setTitle(currentOverlay.getName());
				Marker newMarker = new Marker(LatLng.newInstance(currentOverlay.getLatitude(), currentOverlay.getLongitude()), newMarkerOptions);
				childOverlaysList.add(newMarker);
				manager.overlayMap.put(newMarker, currentOverlay);
				newMarker.addMarkerClickHandler(new RPGMapMarkerClickHandler(manager.parentMapWidget, mapToPlot, currentOverlay));
				manager.parentMapWidget.addOverlay(newMarker);
			}
			manager.overlayList.put(mapToPlot, childOverlaysList);
		}
	}

	public static void hideMapItems(RPGMapMap mapToHide) {
		RPGMapManager manager = RPGMapManager.getInstance();
		ArrayList<Overlay> childOverlayList = manager.overlayList.get(mapToHide);

		if (manager.parentMapWidget != null && mapToHide != null && childOverlayList != null) {
			Iterator<Overlay> childOverlayIterator = childOverlayList.iterator();
			while (childOverlayIterator.hasNext()) {
				Overlay currentOverlay = childOverlayIterator.next();
				manager.parentMapWidget.removeOverlay(currentOverlay);
			}
			manager.overlayList.remove(mapToHide);
		}
	}

	public static ArrayList<RPGMapMap> getRpgMapList() {
		return RPGMapManager.getInstance().rpgMapList;
	}

	public static void setRpgMapList(ArrayList<RPGMapMap> rpgMapList) {
		RPGMapManager.getInstance().rpgMapList = rpgMapList;
	}

	public static void addOverlay(RPGMapMap parentRPGMap, Marker markerToAdd) {
		RPGMapManager manager = RPGMapManager.getInstance();

		manager.parentMapWidget.addOverlay(markerToAdd);
		manager.overlayList.get(parentRPGMap).add(markerToAdd);
		parentRPGMap.addChildMarker(markerToAdd);
	}

	public static void saveMap(RPGMapMap rpgMapMapToSave) {
		RPGMapManager manager = RPGMapManager.getInstance();

		manager.service.saveMap(rpgMapMapToSave, new MapServiceSaveMapAsyncCallback());
	}

	public static void saveOverlay(RPGMapOverlay rpgMapOverLayToSave) {
		RPGMapManager manager = RPGMapManager.getInstance();

		manager.service.saveOverlay(rpgMapOverLayToSave, new MapServiceSaveOverlayAsyncCallback());
	}

	public static void deleteOverlay(Overlay overlayToDelete) {
		RPGMapManager manager = RPGMapManager.getInstance();

		RPGMapOverlay rpgMapOverlayToDelete = manager.overlayMap.get(overlayToDelete);
		
		RPGMapMap parentRPGMapMap = rpgMapOverlayToDelete.getParentRPGMapMap();
		parentRPGMapMap.getChildAttachments().remove(rpgMapOverlayToDelete);

		if (rpgMapOverlayToDelete != null) {
			manager.service.deleteOverlay(rpgMapOverlayToDelete, new MapServiceDeleteOverlayAsyncCallback());
		}
		
		manager.parentMapWidget.removeOverlay(overlayToDelete);
	}

	public static ArrayList<RPGMapUser> getUserList() {
		return RPGMapManager.getInstance().userList;
	}

	public static void setUserList(ArrayList<RPGMapUser> userList) {
		RPGMapManager.getInstance().userList = userList;
	}

	public HashMap<Overlay, RPGMapOverlay> getOverlayMap() {
		return overlayMap;
	}

	public void setOverlayMap(HashMap<Overlay, RPGMapOverlay> overlayMap) {
		this.overlayMap = overlayMap;
	}

}
