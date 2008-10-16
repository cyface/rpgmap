package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.client.entities.Map;
import com.cyface.rpg.map.client.entities.Point;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MapServiceImpl extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceImpl.class);

	
	ArrayList<Map> getAllMaps() {
		ArrayList<Map> resultList = new ArrayList<Map>();
		logger.debug("Aieee!");
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			Query getAllMapsQuery = em.createQuery("select id, name, owner_id from map order by name");
			List rawResultList = getAllMapsQuery.getResultList();
			logger.debug(rawResultList);
			resultList.addAll(rawResultList);
			
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}
	
	ArrayList<Point> getAllPoints() {
		ArrayList<Point> resultList = new ArrayList<Point>();
		logger.debug("Aieee!");
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			Query getAllMapsQuery =  em.createQuery("select id, latitude, longitude, name from point order by name");
			List rawResultList = getAllMapsQuery.getResultList();
			logger.debug(rawResultList);
			resultList.addAll(rawResultList);
			
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}
}
