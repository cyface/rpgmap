package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.test.jpa.AbstractJpaTests;

import com.cyface.rpg.map.domain.entities.RPGMapUser;

public class MapServiceTest extends AbstractJpaTests {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceTest.class);

	MapServiceImpl mapServiceImpl;

	public MapServiceImpl getMapServiceImpl() {
		return mapServiceImpl;
	}

	public void setMapServiceImpl(MapServiceImpl mapServiceImpl) {
		logger.debug("Setting Service Impl!");
		this.mapServiceImpl = mapServiceImpl;
		//try {
		//	this.mapServiceImpl.init();
		//} catch (ServletException e) {
		//	logger.error("Unable to Initialize MapServiceImpl", e);
		//}
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath*:applicationContext-persistence.xml" };
	}

	public void testGetAllUsers() {
		logger.debug("Hello?");
		ArrayList<RPGMapUser> userList = mapServiceImpl.getAllUsers();

		Iterator<RPGMapUser> userListIterator = userList.iterator();
		while (userListIterator.hasNext()) {
			RPGMapUser currentUser = userListIterator.next();
			logger.debug(currentUser);
		}
		assertNotNull(userListIterator);
	}
}
