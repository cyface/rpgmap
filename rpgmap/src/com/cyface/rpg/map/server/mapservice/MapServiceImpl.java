package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;

import net.sf.hibernate4gwt.core.HibernateBeanManager;
import net.sf.hibernate4gwt.gwt.HibernateRemoteService;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.client.mapservice.MapService;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapPoint;

@SuppressWarnings("unchecked")
public class MapServiceImpl extends HibernateRemoteService implements MapService {
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
		HibernateBeanManager.getInstance().setEntityManagerFactory(emf);
	}

	public ArrayList<RPGMapMap> getAllMaps() {
		ArrayList<RPGMapMap> resultList = new ArrayList<RPGMapMap>();
		try {
			Query getAllMapsQuery = em.createQuery("SELECT map FROM RPGMapMap as map ORDER BY name");
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
			Query getAllMapsQuery = em.createNamedQuery("SELECT point FROM RPGMapPoint as point ORDER BY name");
			List rawResultList = getAllMapsQuery.getResultList();
			logger.debug(rawResultList);
			resultList.addAll(rawResultList);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}
}
