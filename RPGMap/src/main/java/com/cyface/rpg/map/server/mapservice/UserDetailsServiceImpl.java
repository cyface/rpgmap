package com.cyface.rpg.map.server.mapservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.sf.hibernate4gwt.core.HibernateBeanManager;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.cyface.rpg.map.domain.entities.RPGMapUser;

public class UserDetailsServiceImpl implements UserDetailsService {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.MapServiceImpl.class);

	EntityManagerFactory emf = null;
	EntityManager em = null;

	public UserDetails loadUserByUsername(String usernameToFind) throws UsernameNotFoundException, DataAccessException {
		emf = Persistence.createEntityManagerFactory("rpgmap");
		em = emf.createEntityManager();
		HibernateBeanManager.getInstance().setEntityManagerFactory(emf);
		Query getUserQuery = em.createQuery("SELECT user FROM RPGMapUser AS user WHERE user.username = ?");
		getUserQuery.setParameter(1, usernameToFind);
		RPGMapUser userObject;
		try {
			userObject = (RPGMapUser) getUserQuery.getSingleResult();
		} catch (Exception e) {
			throw new UsernameNotFoundException("User Not Found", e);
		}
		UserDetails returnUser = new ServerSideUserDetails(userObject);
		return returnUser;
	}
}
