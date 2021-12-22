 package com.bookstore.backend.infrastructure.security.auth;

import com.bookstore.backend.infrastructure.security.service.UserSecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserSecurityService userService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/api/thymeleaf/user/**").permitAll()
        	.antMatchers("/api/thymeleaf/address/form-address").permitAll()
            .antMatchers("/api/thymeleaf/address/save").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/api/thymeleaf/address")
            .and()
            .logout()
            .logoutSuccessUrl("/login");
    }
}
