package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
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
	
	
	public void testGetAllMaps() {
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
}
