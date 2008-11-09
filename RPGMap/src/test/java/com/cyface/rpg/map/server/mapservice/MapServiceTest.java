package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.cyface.rpg.map.domain.entities.RPGMapUser;

public class MapServiceTest extends TestCase {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceTest.class);

	public void testGetAllUsers() {
		MapServiceImpl mapService = new MapServiceImpl();
		try {
			mapService.init();
		} catch (ServletException e) {
			logger.error(e);
		}
		ArrayList<RPGMapUser> userList = mapService.getAllUsers();

		Iterator<RPGMapUser> userListIterator = userList.iterator();
		while (userListIterator.hasNext()) {
			RPGMapUser currentUser = userListIterator.next();
			logger.debug(currentUser);
		}
		assertNotNull(userListIterator);
	}
	
	
	public void testGetAllPublicMaps() {
		MapServiceImpl mapService = new MapServiceImpl();
		try {
			mapService.init();
		} catch (ServletException e) {
			logger.error(e);
		}
		ArrayList<RPGMapMap> mapList = mapService.getAllPublicMaps();

		Iterator<RPGMapMap> mapListIterator = mapList.iterator();
		while (mapListIterator.hasNext()) {
			RPGMapMap currentMap = mapListIterator.next();
			logger.debug(currentMap);
		}
		assertNotNull(mapListIterator);
	}
	
	public void testDeleteOverlay() {
		MapServiceImpl mapService = new MapServiceImpl();
		try {
			mapService.init();
		} catch (ServletException e) {
			logger.error(e);
		}
		ArrayList<RPGMapUser> allUsers = mapService.getAllUsers();
		Iterator<RPGMapUser> allUsersIterator = allUsers.iterator();
		while (allUsersIterator.hasNext()) {
			RPGMapUser currentUser = allUsersIterator.next();
			if ("cyface".equals(currentUser.getUsername())) {
				logger.debug("Delete Test: Found cyface");
				Set<RPGMapMap> userMaps = currentUser.getChildRPGMapMaps();
				Iterator<RPGMapMap> userMapsIterator = userMaps.iterator();
				while (userMapsIterator.hasNext()) {
					RPGMapMap currentMap = userMapsIterator.next();
					if ("Return to Northmoor GM Only".equals(currentMap.getName())) {
						logger.debug("Delete Test: Found Return to Northmoor GM Only");
						Set<RPGMapOverlay> mapOverlays = currentMap.getChildRPGMapOverlays();
						Iterator<RPGMapOverlay> mapOverlaysIterator = mapOverlays.iterator();
						while (mapOverlaysIterator.hasNext()) {
							RPGMapOverlay currentOverlay = mapOverlaysIterator.next();
							logger.debug("on" + currentOverlay.getName());
							if ("deleteTestMarker".equals(currentOverlay.getName())) {
								logger.debug("Delete Test: Found deleteTestMarker");
								mapService.deleteOverlay(currentOverlay);
								logger.debug("Delete Test: deleteTestMarker deleted");
							}
						}
					}
				}
			}
		}
	}
}
