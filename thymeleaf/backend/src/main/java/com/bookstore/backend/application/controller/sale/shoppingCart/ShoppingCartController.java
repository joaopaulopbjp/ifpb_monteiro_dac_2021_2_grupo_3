package com.bookstore.backend.application.controller.sale.shoppingCart;

import java.security.Principal;

import com.bookstore.backend.application.service.sale.shoppingCart.ShoppingCartService;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.sale.ShoppingCartDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {
    
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ShoppingCartDTO dto, Principal principal) {
        ShoppingCartModel shoppingCartModel = (ShoppingCartModel) ModelMapperService.convertToModel(dto, ShoppingCartModel.class);
        
        try {
            shoppingCartModel = shoppingCartService.add(shoppingCartModel, 
                dto.getItemList().get(0).getIdProduct(), principal.getName());
            
            dto = (ShoppingCartDTO) ModelMapperService.convertToDTO(shoppingCartModel, ShoppingCartDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody ShoppingCartDTO dto, Principal principal) {
        try {
            ShoppingCartModel shoppingCartModel = shoppingCartService.remove(dto.getItemList().get(0).getId(), 
                principal.getName());
            
            dto = (ShoppingCartDTO) ModelMapperService.convertToDTO(shoppingCartModel, ShoppingCartDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find-shoppingcart")
    public ResponseEntity<?> findShoppingCart(Principal principal){
        try {
            ShoppingCartModel shoppingCart = shoppingCartService.findShoppingCart(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/get-total")
    public ResponseEntity<?> getTotal(Principal principal){
        try {
            Integer num = shoppingCartService.getTotal(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(new Response(num.toString()));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }
}
