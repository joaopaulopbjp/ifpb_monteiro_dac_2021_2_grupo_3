package com.bookstore.backend.application.controller.address;

import java.util.List;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.address.AddressDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressDTO dto){
        AddressModel addressModel = (AddressModel) ModelMapperService.convertToModel(dto, AddressModel.class);
        try {
            addressModel = addressService.save(addressModel, dto.getPersonId());
            dto = (AddressDTO) ModelMapperService.convertToDTO(addressModel, AddressDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody AddressDTO dto){
        try {
            addressService.delete(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (IllegalArgumentException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        List<AddressModel> list = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestBody Long id){
        try {
            AddressModel address = addressService.findById(id);
            AddressDTO dto = (AddressDTO)ModelMapperService.convertToDTO(address, AddressDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody AddressDTO dto){
        AddressModel address = (AddressModel) ModelMapperService.convertToModel(dto, AddressModel.class);
        try {
            address = addressService.update(address);
            return ResponseEntity.status(HttpStatus.OK).body(address);
        } catch (NotFoundException e) {
            Response response = new Response(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
