package com.bookstore.backend.application.service.login;

import com.bookstore.backend.infrastructure.exception.InvalidCredentialsException;
import com.bookstore.backend.infrastructure.security.auth.JwtUtils;
import com.bookstore.backend.infrastructure.security.service.UserSecurityService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;
import com.bookstore.backend.presentation.dto.login.CredentialResponse;
import com.bookstore.backend.presentation.dto.login.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AdminVerify adminVerify;
    
    public CredentialResponse fazerLogin(CredentialsDTO credentials) throws InvalidCredentialsException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new InvalidCredentialsException("Auth failed for username " + credentials.getUsername());
        }

        UserDetails loadedUser = userSecurityService.loadUserByUsername(username);

        String generatedToken = jwtUtils.generateToken(loadedUser);

        CredentialResponse response = new CredentialResponse(generatedToken, adminVerify.isAdmin(username));
        return response;
    }
}
