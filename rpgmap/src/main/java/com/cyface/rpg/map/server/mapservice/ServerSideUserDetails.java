package com.cyface.rpg.map.server.mapservice;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import com.cyface.rpg.map.domain.entities.RPGMapUser;

public class ServerSideUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private RPGMapUser rpgMapUser;
	
	public ServerSideUserDetails(RPGMapUser rpgMapUser) {
		this.rpgMapUser = rpgMapUser;
	}
	
	public String toString() {
		return this.rpgMapUser.toString();
	}

	public GrantedAuthority[] getAuthorities() {
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		GrantedAuthority newAuthority = new GrantedAuthorityImpl("ROLE_ALL");
		authorities[0] = newAuthority;
		return authorities;
	}

	public String getPassword() {
		return rpgMapUser.getPassword();
	}

	public String getUsername() {
		return rpgMapUser.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return rpgMapUser.isEnabled();
	}

	public void setRpgMapUser(RPGMapUser rpgMapUser) {
		this.rpgMapUser = rpgMapUser;
	}

	public RPGMapUser getRpgMapUser() {
		return rpgMapUser;
	}

}
