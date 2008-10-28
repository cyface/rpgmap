package com.cyface.rpg.map.client.mapmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.cyface.rpg.map.client.handlers.RPGMapMarkerClickHandler;
import com.cyface.rpg.map.client.mapservice.MapService;
import com.cyface.rpg.map.client.mapservice.MapServiceAsync;
import com.cyface.rpg.map.client.mapservice.MapServiceGetAllMapsAsyncCallback;
import com.cyface.rpg.map.client.mapservice.MapServiceSaveMapAsyncCallback;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapPoint;
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
	private HashMap<RPGMapMap, ArrayList<Overlay>> overlayList;
	private MapWidget parentMapWidget;

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

		MapServiceAsync service = (MapServiceAsync) GWT.create(MapService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "mapservice");

		MapServiceGetAllMapsAsyncCallback callback = new MapServiceGetAllMapsAsyncCallback(parentMapWidget);

		service.getAllMaps(callback);
	}

	public static void plotMapItems(RPGMapMap mapToPlot) {
		RPGMapManager manager = RPGMapManager.getInstance();
		Set<RPGMapPoint> childMapPoints = mapToPlot.getChildRPGMapPoints();

		if (manager.parentMapWidget != null && mapToPlot != null && childMapPoints != null) {
			ArrayList<Overlay> childPointOverlayList = new ArrayList<Overlay>();
			Iterator<RPGMapPoint> childPointsIterator = childMapPoints.iterator();
			while (childPointsIterator.hasNext()) {
				RPGMapPoint currentPoint = childPointsIterator.next();
				MarkerOptions newMarkerOptions = MarkerOptions.newInstance();
				newMarkerOptions.setTitle(currentPoint.getName());
				Marker newMarker = new Marker(LatLng.newInstance(currentPoint.getLatitude(), currentPoint.getLongitude()), newMarkerOptions);
				childPointOverlayList.add(newMarker);
				newMarker.addMarkerClickHandler(new RPGMapMarkerClickHandler(manager.parentMapWidget, mapToPlot, currentPoint));
				manager.parentMapWidget.addOverlay(newMarker);
			}
			manager.overlayList.put(mapToPlot, childPointOverlayList);
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

	public static void saveMap(RPGMapMap parentRPGMapMap) {
		MapServiceAsync service = (MapServiceAsync) GWT.create(MapService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "mapservice");

		service.saveMap(parentRPGMapMap, new MapServiceSaveMapAsyncCallback());
	}

}
