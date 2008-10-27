package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapPoint;

public class MapServiceTest extends TestCase {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceTest.class);

	public void testGetAllMaps() {
		MapServiceImpl mapService = new MapServiceImpl();
		try {
			mapService.init();
		} catch (ServletException e) {
			logger.error(e);
		}
		ArrayList<RPGMapMap> mapList = mapService.getAllMaps();

		Iterator<RPGMapMap> mapListIterator = mapList.iterator();
		while (mapListIterator.hasNext()) {
			RPGMapMap currentMap = mapListIterator.next();
			logger.debug(currentMap);
		}
		assertNotNull(mapListIterator);
	}

	public void testGetAllPoints() {
		MapServiceImpl mapService = new MapServiceImpl();
		try {
			mapService.init();
		} catch (ServletException e) {
			logger.error(e);
		}
		ArrayList<RPGMapPoint> pointList = mapService.getAllPoints();

		Iterator<RPGMapPoint> pointListIterator = pointList.iterator();
		while (pointListIterator.hasNext()) {
			RPGMapPoint currentPoint = pointListIterator.next();
			logger.debug(currentPoint);
		}
		assertNotNull(pointListIterator);
	}

}
