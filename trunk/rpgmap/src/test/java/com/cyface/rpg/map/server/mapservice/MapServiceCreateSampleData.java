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
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.cyface.rpg.map.domain.entities.RPGMapUser;

public class MapServiceCreateSampleData extends TestCase {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceCreateSampleData.class);

	public void testAddMainMap() {
		try { // SetUp
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			HibernateBeanManager.getInstance().setEntityManagerFactory(emf);
			et.begin();
			
			// Clean out the user table
			Query truncMapTableQuery = em.createNativeQuery("TRUNCATE TABLE rpgmap.user");
			truncMapTableQuery.executeUpdate();

			// Clean out the map table
			Query truncUserTableQuery = em.createNativeQuery("TRUNCATE TABLE rpgmap.map");
			truncUserTableQuery.executeUpdate();

			// Clean out the overlay table
			Query truncPointTableQuery = em.createNativeQuery("TRUNCATE TABLE rpgmap.overlay");
			truncPointTableQuery.executeUpdate();
			
			// Create the user
			RPGMapUser mainUser = new RPGMapUser();
			mainUser.setName("Tim White");
			mainUser.setUserId("cyface");
			mainUser.setPassword("test");
			em.persist(mainUser);

			// Create the map
			RPGMapMap mainMap = new RPGMapMap();
			mainMap.setName("Return to Northmoor");
			mainMap.setParentRPGMapUser(mainUser);
			em.persist(mainMap);

			/* Create second map with child overlays */
			RPGMapMap secondaryMap = new RPGMapMap();
			secondaryMap.setName("Return to Northmoor GM Only");
			secondaryMap.setParentRPGMapUser(mainUser);

			RPGMapOverlay narrowsMarker = new RPGMapOverlay();
			narrowsMarker.setName("The Narrows 2");
			narrowsMarker.setLatitude(18.18);
			narrowsMarker.setLongitude(-37.0);
			narrowsMarker.setType(RPGMapOverlay.MARKER_TYPE);
			narrowsMarker.setParentRPGMapMap(secondaryMap);
			secondaryMap.addChildRPGMapOverlay(narrowsMarker);

			RPGMapOverlay lostVillageMarker = new RPGMapOverlay();
			lostVillageMarker.setName("The Lost Village 2");
			lostVillageMarker.setLatitude(16.4);
			lostVillageMarker.setLongitude(-40.0);
			lostVillageMarker.setType(RPGMapOverlay.MARKER_TYPE);
			lostVillageMarker.setParentRPGMapMap(secondaryMap);
			secondaryMap.addChildRPGMapOverlay(lostVillageMarker);

			em.persist(secondaryMap);
			
			mainUser.addChildRPGMapMap(mainMap);
			mainUser.addChildRPGMapMap(secondaryMap);

			/* Commit and Clean-Up */
			et.commit();

			RPGMapMap mainMapVerify = em.find(RPGMapMap.class, new Integer(1));
			assertNotNull(mainMapVerify);
			assertEquals("Return to Northmoor", mainMapVerify.getName());

			RPGMapMap secondaryMapVerify = em.find(RPGMapMap.class, new Integer(2));
			assertNotNull(secondaryMapVerify);
			assertEquals("Return to Northmoor GM Only", secondaryMapVerify.getName());
			assertEquals(2, secondaryMapVerify.getChildRPGMapOverlays().size());

			em.close();
			emf.close();
		} catch (Exception e) {
			logger.error("Error Running Add Maps Test", e);
		}
	}

	public void testAddOverlaysToExistingMaps() {
		try {
			// SetUp
			logger.debug("********** Starting testAddOverlaysToExistingMaps ****************");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			HibernateBeanManager.getInstance().setEntityManagerFactory(emf);
			et.begin();

			RPGMapMap mainMap = em.find(RPGMapMap.class, new Integer(1));
			assertNotNull(mainMap);
			assertEquals("Return to Northmoor", mainMap.getName());
			assertEquals(1, mainMap.getParentRPGMapUser().getId());

			/* Persist a point using the parent class */
			RPGMapOverlay narrowsMarker = new RPGMapOverlay();
			narrowsMarker.setName("The Narrows");
			narrowsMarker.setLatitude(13.18);
			narrowsMarker.setLongitude(-39.0);
			narrowsMarker.setType(RPGMapOverlay.MARKER_TYPE);
			narrowsMarker.setParentRPGMapMap(mainMap);
			mainMap.addChildRPGMapOverlay(narrowsMarker);
			em.merge(mainMap);
			
			/* Persist a point directly */
			RPGMapOverlay lostVillageMarker = new RPGMapOverlay();
			lostVillageMarker.setName("The Lost Village");
			lostVillageMarker.setLatitude(17.4);
			lostVillageMarker.setLongitude(-41.0);
			lostVillageMarker.setType(RPGMapOverlay.MARKER_TYPE);
			lostVillageMarker.setParentRPGMapMap(mainMap);
			em.persist(lostVillageMarker);
			em.refresh(mainMap);

			// Commit and Clean-Up
			et.commit();

			/* Verify the data that went in */
			RPGMapMap mainMapVerify = em.find(RPGMapMap.class, new Integer(1));
			logger.debug(mainMapVerify);
			assertNotNull(mainMapVerify);
			assertEquals("Return to Northmoor", mainMapVerify.getName());
			assertEquals(1, mainMapVerify.getParentRPGMapUser().getId());
			Set<RPGMapOverlay> mainMapChildOverlays = mainMapVerify.getChildRPGMapOverlays();
			assertEquals(2, mainMapChildOverlays.size());
			Iterator<RPGMapOverlay> mainMapChildOverlaysIterator = mainMapChildOverlays.iterator();
			while (mainMapChildOverlaysIterator.hasNext()) {
				RPGMapOverlay currentOverlay = mainMapChildOverlaysIterator.next();
				if ("The Narrows".equals(currentOverlay.getName())) {
					assertEquals(13.18, currentOverlay.getLatitude());
					assertEquals(-39.0, currentOverlay.getLongitude());
				} else if ("The Lost Village".equals(currentOverlay.getName())) {
					assertEquals(17.4, currentOverlay.getLatitude());
					assertEquals(-41.0, currentOverlay.getLongitude());
				} else {
					fail("Extra or missing overlay found");
				}
			}
			logger.debug("********** Ending testAddOverlaysToExistingMaps ****************");
			em.close();
			emf.close();
		} catch (Exception e) {
			logger.error("!!!!!!!!! Error Running testAddOverlaysToExistingMaps !!!!!!!!!!!!!!!", e);
		}
	}
}
