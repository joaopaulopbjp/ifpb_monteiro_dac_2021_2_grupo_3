package com.bookstore.backend.application.controller.address.thymeleaf;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.infrastructure.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/addressTh")
@CrossOrigin
@Controller
public class AddressControllerTh {
      
      @Autowired
      private AddressService addressService;

      @GetMapping("/address")
      public String nameView(Model model, Principal principal) {
            try {
                  model.addAttribute("addressList", addressService.findAll(principal.getName()));
            } catch (NotFoundException e) {}
            return "address";
      }
}
