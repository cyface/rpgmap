package com.cyface.rpg.map.client;

import com.cyface.rpg.map.client.mapservice.MapService;
import com.cyface.rpg.map.client.mapservice.MapServiceAsync;
import com.cyface.rpg.map.client.mapservice.MapServiceAsyncCallback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.Copyright;
import com.google.gwt.maps.client.CopyrightCollection;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapTypeOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RPGMap implements EntryPoint {
	private MapWidget map;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		int minZoom = 3;
		int maxZoom = 5;
		int initialZoom = 4;
		double centerLat = 17.4;
		double centerLon = -41;

		map = new MapWidget(LatLng.newInstance(centerLat, centerLon), initialZoom);
		map.setSize("800px", "600px");

		// Add some controls
		map.addControl(new LargeMapControl());
		map.addControl(new MapTypeControl());
		map.setDoubleClickZoom(true);
		map.setContinuousZoom(true);
		map.setScrollWheelZoomEnabled(true);

		Copyright copyright = new Copyright(1, LatLngBounds.newInstance(LatLng.newInstance(-90, -180), LatLng.newInstance(90, 180)), 0, "<a href=\"http://northmoor.spookyouthouse.com\">Return to Northmoor</a>");
		CopyrightCollection copyrightCollection = new CopyrightCollection();
		copyrightCollection.addCopyright(copyright);

		// create a custom picture layer
		TileLayer[] tileLayers = { new RPGMapTileLayer("http://vargoth.com/northmoor/map/Northmoor-tiles/", ".jpg", copyrightCollection, 0, maxZoom + 2) };
		RPGMapProjection rpgMapProjection = new RPGMapProjection(maxZoom + 2, RPGMapProjection.IMAGE_WRAPS);
		MapTypeOptions mapTypeOptions = new MapTypeOptions();
		mapTypeOptions.setMinResolution(minZoom);
		mapTypeOptions.setMaxResolution(maxZoom);
		mapTypeOptions.setErrorMessage("Data not available");
		MapType rpgMapType = new MapType(tileLayers, rpgMapProjection, "Pic", mapTypeOptions);

		map.addMapType(rpgMapType);
		map.setCurrentMapType(rpgMapType);

		map.removeMapType(MapType.getNormalMap());
		map.removeMapType(MapType.getSatelliteMap());
		map.removeMapType(MapType.getHybridMap());

		map.setCenter(LatLng.newInstance(centerLat, centerLon), initialZoom);

		// Marker lostVillageMarker = new Marker(LatLng.newInstance(17.4, -41));
		// map.addOverlay(lostVillageMarker);
		// InfoWindowContent lostVillageContent = new
		// InfoWindowContent("<b>The Lost Village</b>");
		// map.getInfoWindow().open(LatLng.newInstance(17.4, -41.0),
		// lostVillageContent);
		//
		// Marker narrowsMarker = new Marker(LatLng.newInstance(13.18, -39));
		// map.addOverlay(narrowsMarker);

		MapServiceAsync service = (MapServiceAsync) GWT.create(MapService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "mapservice");

		MapServiceAsyncCallback callback = new MapServiceAsyncCallback(map);

		service.getAllMaps(callback);

		// Add the map to the HTML host page
		RootPanel.get().add(map);
	}
}
