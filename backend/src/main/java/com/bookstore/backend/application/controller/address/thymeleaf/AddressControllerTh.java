package com.bookstore.backend.application.controller.address.thymeleaf;

import java.security.Principal;
import java.util.List;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.presentation.dto.address.AddressDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
                  List<AddressModel> list = addressService.findAll(principal.getName());
                  model.addAttribute("addressList", list);

            } catch (NotFoundException e) {}
            return "address";
      }

      @PostMapping("/save")
      public String save(@ModelAttribute("model") AddressModel addressModel , Model model, Principal principal) {
            try {
                  model.addAttribute("addressList", addressService.findAll(principal.getName()));
            } catch (NotFoundException e) {}
            return "";
      }

      @PutMapping("/update")
      public String update(Model model, Principal principal) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            return "address";
      }
}
