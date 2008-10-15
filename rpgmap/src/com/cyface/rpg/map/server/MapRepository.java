package com.cyface.rpg.map.server;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

public class MapRepository {
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
		Map myMap = new Map();
		myMap.setName("MyMap2");
		myMap.setOwnerId(1);
		em.persist(myMap);
	}
}