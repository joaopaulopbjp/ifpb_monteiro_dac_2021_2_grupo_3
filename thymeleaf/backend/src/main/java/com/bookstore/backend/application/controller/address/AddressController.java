package com.bookstore.backend.application.controller.address;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.infrastructure.security.auth.JwtUtils;
import com.bookstore.backend.presentation.dto.address.AddressDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/thymeleaf/address")
@CrossOrigin
@Controller
public class AddressController {
      
      @Autowired
      private AddressService addressService;

      @Autowired
      private JwtUtils jwtUtils;

      @GetMapping
      public String view(@CookieValue(value = "token") String token, Model model, HttpServletRequest request) {
            request.setAttribute("Authorization", token);

            List<AddressModel> addressList = null;
            try {
                  addressList = addressService.findAll(jwtUtils.extractUsername(token));
            } catch (NotFoundException e) {
                  e.printStackTrace();
            }
            model.addAttribute("addressList", addressList);

            return "viewAddress";
      }

      @GetMapping("/form-address")
      public String formAddress(Model model) {
            model.addAttribute("dto", new AddressDTO());
            return "registerAddress";
      }

      @PostMapping("/save")
      public String save(@CookieValue(value = "token") String token, @ModelAttribute("dto") AddressDTO dto , Model model, HttpServletRequest request) {
            try {
                  request.setAttribute("Authorization", token);
                  AddressModel addressModel = (AddressModel) ModelMapperService.convertToModel(dto, AddressModel.class);
                  addressService.save(addressModel, jwtUtils.extractUsername(token));
            } catch (NotFoundException e) {
                  e.printStackTrace();
            }
            return "redirect:";
      }

      @DeleteMapping("/delete")
      public String delete(@CookieValue(value = "token") String token, @ModelAttribute("address") AddressDTO dto , Model model, HttpServletRequest request) {
            request.setAttribute("Authorization", token);

            try {
                  addressService.delete(dto.getId(), jwtUtils.extractUsername(token));
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return "address";
      }

      @PutMapping("/update")
      public String update(Model model, Principal principal) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            return "redirect:";
      }
      
}
