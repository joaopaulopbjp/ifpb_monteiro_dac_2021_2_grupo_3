package com.bookstore.backend.infrastructure.security.service;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.service.person.AdminService;
import com.bookstore.backend.application.service.person.UserService;
import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonModel foundedUser;
        List<SimpleGrantedAuthority> roles = new ArrayList<>();

        try {
            foundedUser = userService.findByUsername(username);
            roles.add(new SimpleGrantedAuthority("USER"));
            return new User(foundedUser.getUsername(), foundedUser.getPassword(), roles);
            
        } catch(NotFoundException e) {
            try {
                foundedUser = adminService.findByUsername(username);
                roles.add(new SimpleGrantedAuthority("ADMIN"));
                return new User(foundedUser.getUsername(), foundedUser.getPassword(), roles);
    
            } catch(NotFoundException e1) {
                throw new UsernameNotFoundException(username);
            }
        }

    }
}
