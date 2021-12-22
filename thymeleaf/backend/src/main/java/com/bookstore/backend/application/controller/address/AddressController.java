package com.bookstore.backend.application.controller.address;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.address.AddressDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/thymeleaf/address")
@Controller
public class AddressController {
      
      @Autowired
      private AddressService addressService;

//      @Autowired
//      private JwtUtils jwtUtils;

      @GetMapping
      public String view(Model model) {

            List<AddressModel> addressList = null;
            try {
                  addressList = addressService.findAll();
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
      public String save(Principal principal, @ModelAttribute("dto") AddressDTO dto , Model model) {
            try {
                  AddressModel addressModel = (AddressModel) ModelMapperService.convertToModel(dto, AddressModel.class);
                  addressService.save(addressModel, principal.getName());
            } catch (NotFoundException e) {
                  e.printStackTrace();
            }
            return "redirect:/api/thymeleaf/address";
      }

      @PostMapping("/delete/{id}")
      public String delete( @PathVariable("id") Long id , Principal principal, HttpServletResponse response) {

            try {
                  addressService.delete(id, principal.getName());
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return "redirect:/api/thymeleaf/address";
      }

      @PostMapping("/update/{id}")
      public String update(@PathVariable("id") Long id, @ModelAttribute("dtoAddress") AddressModel address, Model model, Principal principal) {
            try {
                  address.setId(id);
                  addressService.update(address, principal.getName());
            } catch (NotFoundException e) {
                  e.printStackTrace();
            }
            
            return "redirect:/api/thymeleaf/address";
      }

      @GetMapping("/update-view/{id}")
      public String updateView(@PathVariable("id") Long id, Model model, Principal principal) {
            try {
                  AddressModel address = addressService.findById(id, principal.getName());
                  model.addAttribute("dtoAddress", address);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            
            return "UpdateAddress";
      } 
}
