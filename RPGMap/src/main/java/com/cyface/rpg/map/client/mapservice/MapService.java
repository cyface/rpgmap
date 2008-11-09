package com.cyface.rpg.map.client.mapservice;

import java.util.ArrayList;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.cyface.rpg.map.domain.entities.RPGMapUser;
import com.google.gwt.user.client.rpc.RemoteService;

public interface MapService extends RemoteService {
	ArrayList<RPGMapUser> getAllUsers();
	ArrayList<RPGMapMap> getAllPublicMaps();
	RPGMapMap saveMap(RPGMapMap mapToSave);
	RPGMapOverlay saveOverlay(RPGMapOverlay overlayToSave);
	void deleteOverlay(RPGMapOverlay overlayToDelete);
}
