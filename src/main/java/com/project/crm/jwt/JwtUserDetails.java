package com.project.crm.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.crm.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

	private UUID uuid;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(UUID uuid, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.uuid = uuid;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static JwtUserDetails create(User user) {
		List<GrantedAuthority> authoritiesList = new ArrayList<>();
		user.getUserRoles().forEach(role -> {
			authoritiesList.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new JwtUserDetails(user.getUuid(), user.getUsername(), user.getPassword(), authoritiesList);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return username;
	}
}
