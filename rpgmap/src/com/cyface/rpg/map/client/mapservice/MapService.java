package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;

import com.cyface.rpg.map.client.entities.RPGMapMap;
import com.cyface.rpg.map.client.entities.RPGMapPoint;
import com.google.gwt.user.client.rpc.RemoteService;

public interface MapService extends RemoteService {
	ArrayList<RPGMapMap> getAllMaps();
	ArrayList<RPGMapPoint> getAllPoints();
}
