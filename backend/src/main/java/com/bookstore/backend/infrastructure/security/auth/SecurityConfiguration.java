package com.bookstore.backend.infrastructure.security.auth;

import com.bookstore.backend.infrastructure.security.filter.JwtFilterRequest;
import com.bookstore.backend.infrastructure.security.service.UserSecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserSecurityService userService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        // auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable().authorizeRequests().antMatchers("/api/user/save", "/api/login").permitAll().anyRequest()
        //     .authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
            .antMatchers("/api/category/").hasAnyAuthority("ADMIN")
            // .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
            // .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
            // .antMatchers("/delete/**").hasAuthority("ADMIN")
            .and()
            .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
            .logout(logout -> logout.logoutUrl("/logout")).csrf().disable()
            .authorizeRequests().antMatchers("/api/user/save", "/api/login").permitAll().anyRequest()
            .authenticated()
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
