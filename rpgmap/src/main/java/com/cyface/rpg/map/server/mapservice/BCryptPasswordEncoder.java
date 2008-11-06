package com.cyface.rpg.map.server.mapservice;

import org.springframework.dao.DataAccessException;
import org.springframework.security.providers.encoding.PasswordEncoder;


public class BCryptPasswordEncoder implements PasswordEncoder {

	public String encodePassword(String rawPassword, Object salt) throws DataAccessException {
		return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
	}

	public boolean isPasswordValid(String encodedPassword, String rawPassword, Object salt) throws DataAccessException {
		return BCrypt.checkpw(rawPassword, encodedPassword);
	}

}
