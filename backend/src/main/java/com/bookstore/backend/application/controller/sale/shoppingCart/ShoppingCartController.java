package com.bookstore.backend.application.controller.sale.shoppingCart;

import com.bookstore.backend.application.service.sale.shoppingCart.ShoppingCartService;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.sale.ShoppingCartDTO;

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
    public ResponseEntity<?> add(@RequestBody ShoppingCartDTO dto) {
        ShoppingCartModel shoppingCartModel = (ShoppingCartModel) ModelMapperService.convertToModel(dto, ShoppingCartModel.class);
        
        try {
            shoppingCartModel = shoppingCartService.add(shoppingCartModel,
                dto.getIdPerson(),
                dto.getItemList().get(0).getIdProduct());
            
            dto = (ShoppingCartDTO) ModelMapperService.convertToDTO(shoppingCartModel, ShoppingCartDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody ShoppingCartDTO dto) {
        try {
            ShoppingCartModel shoppingCartModel = shoppingCartService.remove(dto.getIdPerson(),
                dto.getItemList().get(0).getId());
            
            dto = (ShoppingCartDTO) ModelMapperService.convertToDTO(shoppingCartModel, ShoppingCartDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/find-by-user-id")
    public ResponseEntity<?> findByUserId(@RequestBody ShoppingCartDTO dto) {
        try {
            ShoppingCartModel shoppingCartModel = shoppingCartService.findByUser(dto.getIdPerson());
            dto = (ShoppingCartDTO) ModelMapperService.convertToDTO(shoppingCartModel, ShoppingCartDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
