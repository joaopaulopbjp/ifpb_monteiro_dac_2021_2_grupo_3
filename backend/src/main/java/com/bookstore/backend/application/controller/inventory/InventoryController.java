package com.bookstore.backend.application.controller.inventory;

import java.security.Principal;

import com.bookstore.backend.application.service.inventory.InventoryService;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.inventory.InventoryDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/inventory")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PutMapping
    public ResponseEntity<?> update(@RequestBody InventoryDTO dto) {
        InventoryModel inventory = (InventoryModel) ModelMapperService.convertToDTO(dto, InventoryModel.class);
        try {
            inventory = inventoryService.update(inventory);
            dto = (InventoryDTO) ModelMapperService.convertToDTO(inventory, InventoryDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/increment")
    public ResponseEntity<?> increment(@RequestBody InventoryDTO dto, Principal principal) {
        try {
            inventoryService.incremet(dto.getAmount(), dto.getIdBook(), principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/decrement")
    public ResponseEntity<?> decrement(@RequestBody InventoryDTO dto, Principal principal){
        try {
            inventoryService.decrement(dto.getAmount(), dto.getIdBook(), principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(null);

        }catch(NotFoundException e){
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
