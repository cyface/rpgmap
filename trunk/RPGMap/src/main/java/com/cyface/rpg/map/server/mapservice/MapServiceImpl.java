package com.cyface.rpg.map.server.mapservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;

import net.sf.hibernate4gwt.core.HibernateBeanManager;
import net.sf.hibernate4gwt.gwt.HibernateRemoteService;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.client.mapservice.MapService;
import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapUser;

@SuppressWarnings("unchecked")
public class MapServiceImpl extends HibernateRemoteService implements MapService {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceImpl.class);

	EntityManagerFactory emf = null;
	EntityManager em = null;

	@Override
	public void init() throws ServletException {
		super.init();
		emf = Persistence.createEntityManagerFactory("rpgmap");
		em = emf.createEntityManager();
		HibernateBeanManager.getInstance().setEntityManagerFactory(emf);
	}
	
	public ArrayList<RPGMapUser> getAllUsers() {
		ArrayList<RPGMapUser> resultList = new ArrayList<RPGMapUser>();
		try {
			Query getAllUsersQuery = em.createQuery("SELECT user FROM RPGMapUser as user ORDER BY username");
			List rawResultList = getAllUsersQuery.getResultList();
			//logger.debug(rawResultList);
			resultList.addAll(rawResultList);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}

	public ArrayList<RPGMapMap> getAllPublicMaps() {
		ArrayList<RPGMapMap> resultList = new ArrayList<RPGMapMap>();
		try {
			Query getAllPublicMapsQuery = em.createQuery("SELECT map FROM RPGMapMap as map WHERE map.publicallyViewable = true ORDER BY name");
			List rawResultList = getAllPublicMapsQuery.getResultList();
			//logger.debug(rawResultList);
			resultList.addAll(rawResultList);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultList;
	}

	public RPGMapMap saveMap(RPGMapMap mapToSave) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		if (mapToSave.getId() > 0) {
			em.merge(mapToSave);
		} else {
			em.persist(mapToSave);
		}
		et.commit();
		return mapToSave;
	}
}