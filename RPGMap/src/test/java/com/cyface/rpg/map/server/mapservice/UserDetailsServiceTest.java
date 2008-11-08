package com.cyface.rpg.map.server.mapservice;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.security.userdetails.UserDetails;

public class UserDetailsServiceTest extends TestCase {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.UserDetailsServiceTest.class);

	public void testGetUserDetails() {
		UserDetailsServiceImpl userService = new UserDetailsServiceImpl();
		UserDetails cyfaceUserDetails = userService.loadUserByUsername("cyface");
		assertNotNull(cyfaceUserDetails);
		logger.debug(cyfaceUserDetails);
	}
}
