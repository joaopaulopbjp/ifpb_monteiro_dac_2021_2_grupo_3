package com.bookstore.backend.infrastructure.security.service;

import com.bookstore.backend.application.service.person.PersonService;
import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
	@Autowired
	private PersonService userService;

	//    @Autowired
	//    private AdminService adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PersonModel foundedUser;
		//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		try {
			foundedUser = userService.findByUsername(username);
			return new User(foundedUser.getUsername(), foundedUser.getPassword(), foundedUser.getAuthorities());

		} catch(NotFoundException e1) {
			throw new UsernameNotFoundException(username);
		}
	}

}
