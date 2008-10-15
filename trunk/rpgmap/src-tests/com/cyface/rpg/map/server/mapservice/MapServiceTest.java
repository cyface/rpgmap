package com.cyface.rpg.map.server.mapservice;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.server.Map;
import com.cyface.rpg.map.server.Point;


public class MapServiceTest extends TestCase {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceTest.class);

	public void testGetAllMaps() {
		MapServiceImpl mapService = new MapServiceImpl();
		
		ArrayList<Map> mapList = mapService.getAllMaps();
		
		Iterator<Map> mapListIterator = mapList.iterator();
		while (mapListIterator.hasNext()) {
			Map currentMap = mapListIterator.next();
			logger.debug(currentMap);
		}
		assertNotNull(mapListIterator);
	}
	
	public void testGetAllPoints() {
		MapServiceImpl mapService = new MapServiceImpl();
		
		ArrayList<Point> pointList = mapService.getAllPoints();
		
		Iterator<Point> pointListIterator = pointList.iterator();
		while (pointListIterator.hasNext()) {
			Point currentPoint = pointListIterator.next();
			logger.debug(currentPoint);
		}
		assertNotNull(pointListIterator);
	}

}
