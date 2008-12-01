package com.cyface.rpg.map.server.mapservice;

import org.apache.log4j.Logger;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.test.jpa.AbstractJpaTests;

public class UserDetailsServiceTest extends AbstractJpaTests {
	Logger logger = Logger.getLogger(com.cyface.rpg.map.server.mapservice.UserDetailsServiceTest.class);

	UserDetailsServiceImpl userDetailsServiceImpl;
	
	public UserDetailsServiceImpl getUserDetailsServiceImpl() {
		System.out.println("Seeing userdetails service impl!");
		return userDetailsServiceImpl;
	}

	public void setUserDetailsServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl) {
		logger.debug("Setting Service Impl!");
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Override
	protected String[] getConfigLocations() {
		logger.debug("Looking for Config!");
		return new String[] {"classpath*:applicationContext-security-test.xml" };
	}
	
	public void testGetUserDetails() {
		System.out.println("*******testGetUserDetails");
		userDetailsServiceImpl = getUserDetailsServiceImpl();
		UserDetails cyfaceUserDetails = userDetailsServiceImpl.loadUserByUsername("cyface");
		assertNotNull(cyfaceUserDetails);
		logger.debug(cyfaceUserDetails);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		logger.debug("Password Should Be: " + bCryptPasswordEncoder.encodePassword("cyface", cyfaceUserDetails.getUsername()));
		assertTrue(bCryptPasswordEncoder.isPasswordValid(cyfaceUserDetails.getPassword(), "cyface", cyfaceUserDetails.getUsername()));
	}
}
