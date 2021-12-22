package com.bookstore.backend.application.controller.person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.bookstore.backend.application.service.person.PersonService;
import com.bookstore.backend.domain.model.user.PersonModel;
//import com.bookstore.backend.application.service.person.UserService;
//import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.person.PersonDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/api/thymeleaf/user")
public class UserController {

   @Autowired
   private PersonService personService;
   
   @PostMapping("/save")
   public String save(@ModelAttribute("dto") PersonModel dto , Model model) {
       try {
           personService.save(dto);
       } catch (IllegalArgumentException e) {
           e.printStackTrace();
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       return "redirect:http://localhost:8080/login";
   }

   @GetMapping("/save-form")
   public String saveForm(Model model) {
       model.addAttribute("dto", new PersonModel());
       return "Register";
   }
}
