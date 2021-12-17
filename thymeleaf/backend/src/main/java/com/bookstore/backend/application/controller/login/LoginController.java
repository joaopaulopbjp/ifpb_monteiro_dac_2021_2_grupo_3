package com.bookstore.backend.application.controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.backend.application.service.login.LoginService;
import com.bookstore.backend.presentation.dto.login.CredentialsDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/thymeleaf")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login-form")
    public String loginForm(Model model) {
        model.addAttribute("dtoLogin", new CredentialsDTO());
        return "Login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("dtoLogin") CredentialsDTO loginDto, Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", loginService.fazerLogin(loginDto).getToken());
        response.addCookie(cookie);
        return "address";
    }
}
