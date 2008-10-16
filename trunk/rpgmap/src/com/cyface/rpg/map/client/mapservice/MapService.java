package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;

import com.cyface.rpg.map.client.entities.Map;
import com.google.gwt.user.client.rpc.RemoteService;

public interface MapService extends RemoteService {
	ArrayList<Map> getAllMaps();
}
