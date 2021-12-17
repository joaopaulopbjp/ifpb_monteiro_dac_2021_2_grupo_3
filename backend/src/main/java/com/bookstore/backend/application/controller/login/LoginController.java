package com.bookstore.backend.application.controller.login;

import com.bookstore.backend.application.service.login.LoginService;
import com.bookstore.backend.infrastructure.exception.InvalidCredentialsException;
import com.bookstore.backend.presentation.dto.login.CredentialsDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDTO credentials) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loginService.fazerLogin(credentials));
        } catch (InvalidCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }
}
