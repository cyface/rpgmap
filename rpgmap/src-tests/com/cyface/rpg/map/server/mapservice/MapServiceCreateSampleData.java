package com.cyface.rpg.map.server.mapservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.cyface.rpg.map.client.entities.RPGMapMap;
import com.cyface.rpg.map.client.entities.RPGMapPoint;

public class MapServiceCreateSampleData extends TestCase {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceCreateSampleData.class);
	
	public void testAddMainMap() {
		try {
			// SetUp
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			//Clean out the table
			Query truncMapTableQuery = em.createNativeQuery("TRUNCATE TABLE rpgmap.map");
			truncMapTableQuery.executeUpdate();
			
			//Create the record
			RPGMapMap mainMap = new RPGMapMap();
			mainMap.setName("Return to Northmoor");
			mainMap.setOwnerId(1);
			em.persist(mainMap);
			
			// Commit and Clean-Up
			et.commit();
			em.close();
			emf.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public void testAddSamplePoints() {
		try {
			// SetUp
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			//Clean out the table
			Query truncPointTableQuery = em.createNativeQuery("TRUNCATE TABLE rpgmap.point");
			truncPointTableQuery.executeUpdate();
			
			RPGMapMap mainMap = em.find(RPGMapMap.class, new Integer(1));
			
			//Create the records
			RPGMapPoint lostVillagePoint = new RPGMapPoint();
			lostVillagePoint.setName("The Lost Village");
			lostVillagePoint.setLatitude(17.4);
			lostVillagePoint.setLongitude(-41.0);
			lostVillagePoint.setParentRPGMapMap(mainMap);
			em.persist(lostVillagePoint);
			
			RPGMapPoint narrowsPoint = new RPGMapPoint();
			narrowsPoint.setName("The Narrows");
			narrowsPoint.setLatitude(13.18);
			narrowsPoint.setLongitude(-39.0);
			narrowsPoint.setParentRPGMapMap(mainMap);
			em.persist(narrowsPoint);
			
			// Commit and Clean-Up
			et.commit();
			em.close();
			emf.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}
}