package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.server.Map;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MapServiceImpl extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceImpl.class);

	
	ArrayList<Map> getAllMaps() {
		ArrayList<Map> resultList = new ArrayList<Map>();
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			Map myMap = em.find(Map.class, new Map(1));
			logger.debug(myMap);
			
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}
}
