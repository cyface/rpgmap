package com.cyface.rpg.map.server.mapservice;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.TestCase;
import net.sf.hibernate4gwt.core.HibernateBeanManager;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.domain.entities.RPGMapMap;
import com.cyface.rpg.map.domain.entities.RPGMapPoint;


public class MapServiceCreateSampleData extends TestCase {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceCreateSampleData.class);
	
	public void testAddMainMap() {
		try {
			// SetUp
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			HibernateBeanManager.getInstance().setEntityManagerFactory(emf);
			et.begin();
			
			//Clean out the map table
			Query truncMapTableQuery = em.createNativeQuery("TRUNCATE TABLE rpgmap.map");
			truncMapTableQuery.executeUpdate();
			
			//Clean out the points table
			Query truncPointTableQuery = em.createNativeQuery("TRUNCATE TABLE rpgmap.point");
			truncPointTableQuery.executeUpdate();
			
			//Create the record
			RPGMapMap mainMap = new RPGMapMap();
			mainMap.setName("Return to Northmoor");
			mainMap.setOwnerId(1);
			em.persist(mainMap);
			
			RPGMapMap secondaryMap = new RPGMapMap();
			secondaryMap.setName("Return to Northmoor GM Only");
			secondaryMap.setOwnerId(1);
			
			RPGMapPoint narrowsPoint = new RPGMapPoint();
			narrowsPoint.setName("The Narrows");
			narrowsPoint.setLatitude(13.18);
			narrowsPoint.setLongitude(-39.0);
			narrowsPoint.setParentRPGMapMap(secondaryMap);
			secondaryMap.addChildRPGMapPoint(narrowsPoint);
			
			RPGMapPoint lostVillagePoint = new RPGMapPoint();
			lostVillagePoint.setName("The Lost Village");
			lostVillagePoint.setLatitude(17.4);
			lostVillagePoint.setLongitude(-41.0);
			lostVillagePoint.setParentRPGMapMap(secondaryMap);
			secondaryMap.addChildRPGMapPoint(lostVillagePoint);
			
			em.persist(secondaryMap);
			
			// Commit and Clean-Up
			et.commit();
			
			RPGMapMap mainMapVerify = em.find(RPGMapMap.class, new Integer(1));
			assertNotNull(mainMapVerify);
			assertEquals("Return to Northmoor", mainMapVerify.getName());
			assertEquals(1, mainMapVerify.getOwnerId());
			
			RPGMapMap secondaryMapVerify = em.find(RPGMapMap.class, new Integer(2));
			assertNotNull(secondaryMapVerify);
			assertEquals("Return to Northmoor GM Only", secondaryMapVerify.getName());
			assertEquals(1, secondaryMapVerify.getOwnerId());
			
			em.close();
			emf.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public void testAddPointsToExistingMaps() {
		try {
			// SetUp
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			RPGMapMap mainMap = em.find(RPGMapMap.class, new Integer(1));
			assertNotNull(mainMap);
			assertEquals("Return to Northmoor", mainMap.getName());
			assertEquals(1, mainMap.getOwnerId());
			
			/* Persist a point using the parent class */
			RPGMapPoint newPoint = new RPGMapPoint();
			newPoint.setLatitude(13.18);
			newPoint.setLongitude(-39.0);
			newPoint.setName("The Narrows");
			mainMap.addChildRPGMapPoint(newPoint);
			em.merge(mainMap);
			
			/* Persist a point directly */
			RPGMapPoint lostVillagePoint = new RPGMapPoint();
			lostVillagePoint.setName("The Lost Village");
			lostVillagePoint.setLatitude(17.4);
			lostVillagePoint.setLongitude(-41.0);
			lostVillagePoint.setParentRPGMapMap(mainMap);
			em.persist(lostVillagePoint);
			
			// Commit and Clean-Up
			et.commit();
			
			RPGMapMap mainMapVerify = em.find(RPGMapMap.class, new Integer(1));
			assertNotNull(mainMapVerify);
			assertEquals("Return to Northmoor", mainMapVerify.getName());
			assertEquals(1, mainMapVerify.getOwnerId());
			Set<RPGMapPoint> mainMapChildPoints = mainMapVerify.getChildRPGMapPoints();
			assertEquals(2, mainMapChildPoints.size());
			Iterator<RPGMapPoint> mainMapChildPointsIterator = mainMapChildPoints.iterator();
			while (mainMapChildPointsIterator.hasNext()) {
				RPGMapPoint currentPoint = mainMapChildPointsIterator.next();
				if ("The Narrows".equals(currentPoint.getName())) {
					assertEquals(13.18, currentPoint.getLatitude());
					assertEquals(-39.0, currentPoint.getLongitude());
				} else if ("The Lost Village".equals(currentPoint.getName())){
					assertEquals(17.4, currentPoint.getLatitude());
					assertEquals(-41.0, currentPoint.getLongitude());
				} else {
					fail("Extra or missing point found");
				}
			}
			
			em.close();
			emf.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
