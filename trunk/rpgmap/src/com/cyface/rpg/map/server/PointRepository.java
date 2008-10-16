package com.cyface.rpg.map.server;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

import com.cyface.rpg.map.client.entities.Point;

public class PointRepository {
	public static void main(String args[]) {
		try {
			// SetUp
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgmap");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			// Create our Account Record
			AddAccount(em);
			// Commit and Clean-Up
			et.commit();
			em.close();
			emf.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void AddAccount(EntityManager em) {
		Point myPoint = new Point();
		myPoint.setName("MyPoint");
		myPoint.setLatitude(31.00393);
		myPoint.setLongitude(-131.00664);
		em.persist(myPoint);
	}
}