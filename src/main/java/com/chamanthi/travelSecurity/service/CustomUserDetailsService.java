package com.chamanthi.travelSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.chamanthi.travelSecurity.entity.User;
import com.chamanthi.travelSecurity.repository.UserRepository;




@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String input) {
		//User user = userRepository.findByUsername(input);
		User user = userRepository.findByEmail(input);

		if (user == null)
			throw new BadCredentialsException("Bad credentials");

		//checking user name and password is correct
		new AccountStatusUserDetailsChecker().check(user);

		return user;
	}
}
