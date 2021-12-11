package com.bookstore.backend.application.controller.address.thymeleaf;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.address.AddressDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

      @PostMapping(value="/register")
      public String register(@RequestBody AddressDTO dto,Principal principal) {
            try {
                  AddressModel model = (AddressModel) ModelMapperService.convertToModel(dto, AddressModel.class);
                  addressService.save(model, principal.getName());
            } catch (NotFoundException e) {
                  e.printStackTrace();
            }
            return "salvo";
      }
      
}
