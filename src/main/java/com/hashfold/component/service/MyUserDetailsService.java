package com.hashfold.component.service;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hashfold.component.model.user.User;
import com.hashfold.component.persistence.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true,
				true, true, getAuthorities(ROLE_USER));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

}