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
import com.cyface.rpg.map.client.mapservice.MapService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("unchecked")
public class MapServiceImpl extends RemoteServiceServlet implements MapService {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceImpl.class);

	EntityManagerFactory emf = null;
	EntityManager em = null;

	@Override
	public void init() throws ServletException {
		logger.error("ABOUT TO INIT");
		super.init();
		emf = Persistence.createEntityManagerFactory("rpgmap");
		em = emf.createEntityManager();
	}

	public ArrayList<RPGMapMap> getAllMaps() {
		ArrayList<RPGMapMap> resultList = new ArrayList<RPGMapMap>();
		try {
			Query getAllMapsQuery = em.createNamedQuery("RPGMapMap.getAll");
			List rawResultList = getAllMapsQuery.getResultList();
			logger.debug(rawResultList);
			resultList.addAll(rawResultList);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}

	public ArrayList<RPGMapPoint> getAllPoints() {
		ArrayList<RPGMapPoint> resultList = new ArrayList<RPGMapPoint>();
		try {
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
