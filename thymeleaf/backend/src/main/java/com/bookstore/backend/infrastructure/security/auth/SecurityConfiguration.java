 package com.bookstore.backend.infrastructure.security.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.backend.infrastructure.security.service.UserSecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserSecurityService userService;

//    @Autowired
//    private JwtFilterRequest jwtFilterRequest;
    
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return authProvider;
//    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//    	auth.authenticationProvider(authenticationProvider());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/api/thymeleaf/address").permitAll()
        	.antMatchers("/api/thymeleaf/address/form-address").permitAll()
            .antMatchers(HttpMethod.POST,"/api/thymeleaf/address/save").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/api/thymeleaf/address")
            .and()
            .logout();
            
            // .and()
            
            // .exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            //     @Override
            //     public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            //         response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    
            //     }
            // })
            // .and()
            // .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            //     @Override
            //     public void commence(HttpServletRequest request, HttpServletResponse response,
            //                 AuthenticationException authException) throws IOException, ServletException {
            //             // response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            //             System.out.println("ala");
            //             response.sendRedirect("/api/thymeleaf/login-form");
                    
            //     }
            // });
            

        // http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }


	/** Para facilitar a geração de senha encriptada */
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}
    
    

}
