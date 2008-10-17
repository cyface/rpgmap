package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.client.entities.RPGMapMap;
import com.cyface.rpg.map.client.entities.RPGMapPoint;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("unchecked")
public class MapServiceImpl extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceImpl.class);

	EntityManagerFactory emf = null;
	EntityManager em = null;

	@Override
	public void init() throws ServletException {
		super.init();
		emf = Persistence.createEntityManagerFactory("rpgmap");
		em = emf.createEntityManager();
	}

	ArrayList<RPGMapMap> getAllMaps() {
		ArrayList<RPGMapMap> resultList = new ArrayList<RPGMapMap>();
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			Query getAllMapsQuery = em.createNamedQuery("RPGMapMap.getAll");
			List rawResultList = getAllMapsQuery.getResultList();
			logger.debug(rawResultList);
			resultList.addAll(rawResultList);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}

	ArrayList<RPGMapPoint> getAllPoints() {
		ArrayList<RPGMapPoint> resultList = new ArrayList<RPGMapPoint>();
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			Query getAllMapsQuery = em.createNamedQuery("RPGMapPoint.getAll");
			List rawResultList = getAllMapsQuery.getResultList();
			logger.debug(rawResultList);
			resultList.addAll(rawResultList);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}
}
